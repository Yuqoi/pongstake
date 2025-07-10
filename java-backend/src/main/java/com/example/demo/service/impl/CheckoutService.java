package com.example.demo.service.impl;


import com.example.demo.dto.OrderDto;
import com.example.demo.request.OrderRequest;
import com.example.demo.exceptions.ExpiredSessionNotFoundException;
import com.example.demo.helpers.CalculateCost;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PriceRepository;
import com.example.demo.service.ICheckoutService;
import com.example.demo.types.Status;
import com.stripe.exception.InvalidRequestException;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CheckoutService implements ICheckoutService {

    private static final Logger log = LoggerFactory.getLogger(CheckoutService.class);
    @Value("${stripe.success.url}")
    String successUrl;

    @Value("${stripe.cancel.url}")
    String cancelUrl;

    @Autowired
    private final OrderRepository orderRepository;

    public CheckoutService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public String createCheckoutSession(OrderDto orderRequest) throws StripeException {

        Long totalAmount = CalculateCost.calculateCost(orderRequest);

        SessionCreateParams params = SessionCreateParams.builder()
                .setSuccessUrl(successUrl + "/{CHECKOUT_SESSION_ID}")
                .setCancelUrl(cancelUrl)
                .setCustomerEmail(orderRequest.getEmail())
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(orderRequest.getCurrency().name())
                                                .setUnitAmount(totalAmount)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Prediction Bundle").build()
                                                ).build()
                                ).build()
                ).build();

        Session session = Session.create(params);
        Order order = Order.builder()
                .amount(orderRequest.getAmount())
                .price(session.getAmountTotal())
                .metadata(orderRequest.getMetadata())
                .email(session.getCustomerEmail())
                .currency(orderRequest.getCurrency())
                .status(Status.WAITING)
                .paymentId(session.getId())
                .build();

        orderRepository.save(order);

        return session.getUrl();
    }

    @Override
    public Session retrieveCheckoutSession(String sessionId) throws StripeException {
        return Session.retrieve(sessionId);
    }

    @Override
    public Session expireCheckoutSession(String sessionId) throws StripeException {
        try{
            Session session = Session.retrieve(sessionId);

            Optional<Order> foundOrder = orderRepository.findByPaymentId(sessionId);
            if ( foundOrder.isPresent() ) {
                Order order = foundOrder.get();

                order.setStatus(Status.FAILED);
                orderRepository.saveAndFlush(order);

                log.info("Expired Session id: {}, Setting payment as FAILED", order.getPaymentId());
            }

            return session.expire();

        }catch (InvalidRequestException e){
            throw new ExpiredSessionNotFoundException("Wrong Session ID, try another one");
        }
    }


}

package com.example.demo.service.impl;


import com.example.demo.dto.OrderDto;
import com.example.demo.helpers.CalculateCost;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.PriceRepository;
import com.example.demo.service.ICheckoutService;
import com.example.demo.types.Status;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.session.SessionProperties;
import org.springframework.stereotype.Service;


@Service
public class CheckoutService implements ICheckoutService {

    private static final Logger log = LoggerFactory.getLogger(CheckoutService.class);
    @Value("${stripe.success.url}")
    String successUrl;

    @Value("${stripe.cancel.url}")
    String cancelUrl;

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public String createCheckoutSession(OrderDto orderDto) throws StripeException {

        Long totalAmount = CalculateCost.calculateCost(orderDto);

        SessionCreateParams params = SessionCreateParams.builder()
                .setSuccessUrl(successUrl + "/{CHECKOUT_SESSION_ID}")
                .setCancelUrl(cancelUrl)
                .setCustomerEmail(orderDto.getEmail())
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(orderDto.getCurrency().name())
                                                .setUnitAmount(totalAmount)
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Prediction Bundle").build()
                                                ).build()
                                ).build()
                ).build();

        Session session = Session.create(params);

        orderRepository.save(Order.builder()
                .amount(orderDto.getAmount())
                .price(session.getAmountTotal())
                .metadata(orderDto.getMetadata())
                .email(session.getCustomerEmail())
                .currency(orderDto.getCurrency())
                .status(Status.WAITING)
                .paymentId(session.getId())
                .build()
        );

        /**
         * TODO: Save to db an order with status of waiting
         */

        return session.getUrl();
    }

    @Override
    public Session retrieveCheckoutSession(String sessionId) throws StripeException {
        return Session.retrieve(sessionId);
    }

    @Override
    public Session expireCheckoutSession(String sessionId) throws StripeException {
        Session session = Session.retrieve(sessionId);
        return session.expire();
    }


}

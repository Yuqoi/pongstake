package com.example.demo.service.impl;


import com.example.demo.dto.OrderDto;
import com.example.demo.helpers.CalculateCost;
import com.example.demo.repository.PriceRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class CheckoutService implements ICheckoutService {

    @Value("${stripe.success.url}")
    String successUrl;

    @Value("${stripe.cancel.url}")
    String cancelUrl;

    @Autowired
    private PriceRepository priceRepository;

    @Override
    public String createCheckoutSession(OrderDto orderDto) throws StripeException {

        SessionCreateParams params = SessionCreateParams.builder()
                .setMode(SessionCreateParams.Mode.PAYMENT)
                .setSuccessUrl(successUrl)
                .setCancelUrl(cancelUrl)
                .addLineItem(
                        SessionCreateParams.LineItem.builder()
                                .setQuantity(1L)
                                .setPriceData(
                                        SessionCreateParams.LineItem.PriceData.builder()
                                                .setCurrency(orderDto.getCurrency().name())
                                                .setUnitAmount(CalculateCost.calculateCost(orderDto))
                                                .setProductData(
                                                        SessionCreateParams.LineItem.PriceData.ProductData.builder()
                                                                .setName("Prediction").build()
                                                ).build()
                                ).build()
                ).build();


        Session session = Session.create(params);
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

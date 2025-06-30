package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

public interface ICheckoutService {

    String createCheckoutSession(OrderDto orderDto) throws StripeException;
    Session retrieveCheckoutSession(String sessionId) throws StripeException;
    Session expireCheckoutSession(String sessionId) throws StripeException;

}

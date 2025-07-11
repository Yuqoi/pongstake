package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.request.OrderRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

public interface ICheckoutService {

    String createCheckoutSession(OrderDto orderRequest) throws StripeException;
    Session retrieveCheckoutSession(String sessionId) throws StripeException;
    Session expireCheckoutSession(String sessionId) throws StripeException;

}

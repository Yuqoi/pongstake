package com.example.demo.service;

import com.example.demo.model.Order;
import com.example.demo.response.PaymentResponse;
import com.stripe.exception.StripeException;


public interface IPaymentService {

    public PaymentResponse createPaymentLink(Order order) throws StripeException;

}

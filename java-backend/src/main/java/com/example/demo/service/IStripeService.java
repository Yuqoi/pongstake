package com.example.demo.service;

import com.example.demo.dto.PaymentDto;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;

public interface IStripeService {

    PaymentIntent paymentIntent(PaymentDto paymentIntentDto) throws StripeException;
    PaymentIntent confirm(String id) throws StripeException;
    PaymentIntent cancel(String id) throws StripeException;

}

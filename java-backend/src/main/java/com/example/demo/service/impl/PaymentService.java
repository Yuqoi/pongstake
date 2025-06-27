package com.example.demo.service.impl;

import com.example.demo.model.Order;
import com.example.demo.response.PaymentResponse;
import com.example.demo.service.IPaymentService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;
import org.springframework.stereotype.Service;


@Service
public class PaymentService implements IPaymentService {

    @Override
    public PaymentResponse createPaymentLink(Order order) throws StripeException {

//        // TODO: Change later to all payment method type
//        SessionCreateParams params = SessionCreateParams.builder()
//                .addPaymentMethodType(
//                        SessionCreateParams.
//                        PaymentMethodType.CARD
//                )
//                .setMode(SessionCreateParams.Mode.PAYMENT)
//                .setSuccessUrl("http://localhost:3000/payment/success/"+order.getPaymentId())
//                .setCancelUrl("http://localhost:3000/payment/cancel")
//                .addLineItem(SessionCreateParams.LineItem.builder()
//                        .setQuantity(1L)
//                        .setPriceData(SessionCreateParams.LineItem.PriceData.builder()
//                                .setCurrency(order.getCurrency().name())
//                                .setUnitAmount((long) (199 * order.getAmount() * 100))
//                                .setProductData(SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                                        .setName("Prediction")
//                                        .build()
//                                ).build()
//                        ).build()
//                ).build();
//
//        Session session = Session.create(params);
//
//        PaymentResponse response = new PaymentResponse();
//        response.setPaymentUrl(session.getUrl());
//
//        return response;
        return null;
    }
}

package com.example.demo.service.impl;


import com.example.demo.dto.ChargeRequest;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class StripeService {

    private static final Logger log = LoggerFactory.getLogger(StripeService.class);


    public Charge charge(ChargeRequest chargeRequest) throws StripeException {
            Map<String, Object> chargeParams = new HashMap<>();
            chargeParams.put("amount", chargeRequest.getAmount());
            chargeParams.put("currency", chargeRequest.getCurrency());
            chargeParams.put("source", chargeRequest.getStripeToken());
            return Charge.create(chargeParams);
    }

//    public StripeResponse checkoutProduct(ProductRequest request){
//        Stripe.apiKey = apiKey;
//
//        SessionCreateParams.LineItem.PriceData.ProductData productData = SessionCreateParams.LineItem.PriceData.ProductData.builder()
//                .setName(request.getName()).build();
//
//        SessionCreateParams.LineItem.PriceData priceData = SessionCreateParams.LineItem.PriceData.builder()
//                .setCurrency(request.getCurrency() == null ? "EUR" : request.getCurrency())
//                .setUnitAmount(request.getAmount())
//                .setProductData(productData)
//                .build();
//
//        SessionCreateParams.LineItem lineItem = SessionCreateParams.LineItem.builder()
//                .setQuantity(request.getQuantity())
//                .setPriceData(priceData)
//                .build();
//
//        SessionCreateParams params = SessionCreateParams.builder()
//                .setMode(SessionCreateParams.Mode.PAYMENT)
//                .setSuccessUrl("http://localhost:8080/success")
//                .setCancelUrl("http://localhost:8080/cancel")
//                .addLineItem(lineItem)
//                .build();
//
//        // TODO: Handle exception
//
//
//        Session session = null;
//        try {
//            session = Session.create(params);
//            return new StripeResponse.Builder()
//                    .status("SUCCESS")
//                    .message("Payment session created")
//                    .sessionId(session.getId())
//                    .sessionUrl(session.getUrl())
//                    .build();
//        } catch (StripeException e) {
//            log.info("Error in the session: {}", e.getMessage());
//            throw new RuntimeException(e);
//        }
//
//
//
//    }

}

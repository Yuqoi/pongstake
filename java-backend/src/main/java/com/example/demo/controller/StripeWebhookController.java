package com.example.demo.controller;


import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.types.Status;
import com.stripe.model.Event;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/stripe/webhook")
public class StripeWebhookController {

    private static final Logger log = LoggerFactory.getLogger(StripeWebhookController.class);

    @Value("${stripe.webhook.secret}")
    private String WEBHOOK_SECRET;

    @Autowired
    private OrderRepository orderRepository;

    @Transactional
    @PostMapping
    public Map<String, String> handleWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader){
        Map<String, String> response = new HashMap<>();

        log.info("Received webhook payload");
        try {
            Event event = Webhook.constructEvent(payload, sigHeader, WEBHOOK_SECRET);
            log.info("EVENT: {}",event.getType());
            if ("checkout.session.completed".equals(event.getType())){
                String sessionId = event.getRawJsonObject()
                        .get("data").getAsJsonObject()
                        .get("object").getAsJsonObject()
                        .get("id").getAsString();


                Order foundOrder = orderRepository.findByPaymentId(sessionId);
                foundOrder.setStatus(Status.COMPLETED);

                // Save and flush used for making sure that if error occurs it will update in database
                orderRepository.saveAndFlush(foundOrder);

                log.info("Payment successful for session id: {}", sessionId);

                response.put("status", "Payment Successful for Session ID: " + sessionId);
            }
        } catch (Exception e){
            log.error("Webhook error: {}", e.getMessage());
            response.put("Error", "Webhook error: " + e.getMessage());
        }
        return response;
    }

}

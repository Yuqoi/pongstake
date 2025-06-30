package com.example.demo.controller;


import com.stripe.model.Event;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/stripe/webhook")
public class StripeWebhookController {

    private static final Logger log = LoggerFactory.getLogger(StripeWebhookController.class);

    @Value("${stripe.webhook.secret}")
    private String WEBHOOK_SECRET;

    @PostMapping
    public Map<String, String> handleWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader){
        Map<String, String> response = new HashMap<>();

        log.info("Received webhook payload");
        try {
            Event event = Webhook.constructEvent(payload, sigHeader, WEBHOOK_SECRET);

            if ("checkout.session.completed".equals(event.getType())){

                Session session = (Session) event.getDataObjectDeserializer().getObject().get();
                log.info("Payment successful for session id: {}", session.getId());

                /**
                 * TODO: Update data in db to completed
                 */

                response.put("status", "Payment Successful for Session ID: " + session.getId());

            }
        } catch (Exception e){
            log.error("Webhook error: {}", e.getMessage());
            response.put("Error", "Webhook error: " + e.getMessage());
        }
        return response;
    }

}

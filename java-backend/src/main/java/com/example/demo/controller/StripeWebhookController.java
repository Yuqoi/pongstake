package com.example.demo.controller;


import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.types.Status;
import com.example.demo.util.OrderKafkaSender;
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
    private final OrderRepository orderRepository;

    @Autowired
    private final OrderKafkaSender orderKafkaSender;

    public StripeWebhookController(OrderRepository orderRepository, OrderKafkaSender orderKafkaSender) {
        this.orderRepository = orderRepository;
        this.orderKafkaSender = orderKafkaSender;
    }


    @PostMapping
    public Map<String, String> handleWebhook(@RequestBody String payload, @RequestHeader("Stripe-Signature") String sigHeader){
        Map<String, String> response = new HashMap<>();

        log.info("Received webhook payload");
        try {
            Event event = Webhook.constructEvent(payload, sigHeader, WEBHOOK_SECRET);
            if ("checkout.session.completed".equals(event.getType())){

                String sessionId = event.getRawJsonObject()
                        .get("data").getAsJsonObject()
                        .get("object").getAsJsonObject()
                        .get("id").getAsString();

                Optional<Order> foundOrder = orderRepository.findByPaymentId(sessionId);

                if (foundOrder.isPresent()){
                    Order order = foundOrder.get();

                    order.setStatus(Status.COMPLETED);
                    orderRepository.saveAndFlush(order);

                    String result = orderKafkaSender.send(order);
                    log.info("Payment successful for session id: {}\n and kafka result: {}", sessionId, result);
                    response.put("status", "Payment Successful for Session ID: " + sessionId);
                }else{
                    response.put("status", "Payment not successful");
                }
            }
        } catch (Exception e){
            log.error("Webhook error: {}", e.getMessage());
            response.put("Error", "Webhook error: " + e.getMessage());
        }
        return response;
    }

}

package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.response.CheckoutSessionResponse;
import com.example.demo.service.impl.CheckoutService;
import com.example.demo.util.OrderDtoChecker;
import com.example.demo.util.OrderKafkaSender;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/stripe")
@Controller
@CrossOrigin(origins = "*")
public class StripeController {

    private static final Logger log = LoggerFactory.getLogger(StripeController.class);

    @Autowired
    private CheckoutService checkoutService;


    /**
     * TODO: Make /success/{SESSION_ID} so it will only work for valid session id's
     */
    @GetMapping("/success")
    public String success(){
        return "success";
    }

    @GetMapping("/cancel")
    public String cancel(){
        return "cancel";
    }


    @PostMapping("/create-session")
    @ResponseBody
    public ResponseEntity<?> createCheckoutSession(@Valid @RequestBody OrderDto orderDto) throws StripeException {
        OrderDtoChecker.validateOrderDto(orderDto);

        String sessionUrl = checkoutService.createCheckoutSession(orderDto);

        return ResponseEntity
                .ok()
                .body(
                        Map.of("checkoutURL", sessionUrl)
                );
    }


    @GetMapping("/get-session/{sessionId}")
    @ResponseBody
    public CheckoutSessionResponse retrieveCheckoutSession(@PathVariable String sessionId) throws StripeException {
        Session session = checkoutService.retrieveCheckoutSession(sessionId);
        return new CheckoutSessionResponse(
                session.getId(),
                session.getPaymentStatus(),
                session.getAmountTotal(),
                session.getCurrency(),
                session.getStatus(),
                session.getCustomerDetails().getEmail()
        );
    }

    @PostMapping("/expire-session/{sessionId}")
    @ResponseBody
    public ResponseEntity<?> expireSession(@PathVariable String sessionId) throws StripeException {
        Session expiredSession = checkoutService.expireCheckoutSession(sessionId);

        return ResponseEntity.ok().body(
                Map.of(
                        "sessionId", expiredSession.getId(),
                        "status", expiredSession.getStatus()
                )
        );
    }


}

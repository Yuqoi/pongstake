package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.model.CheckoutSessionResponse;
import com.example.demo.service.impl.CheckoutService;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@RestController
@RequestMapping("/api/stripe")
@Controller
@CrossOrigin(origins = "*")
public class StripeController {

    @Autowired
    private CheckoutService checkoutService;

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
    public Map<String, String> createCheckoutSession(@RequestBody OrderDto orderDto) throws StripeException {
        String sessionUrl = checkoutService.createCheckoutSession(orderDto);

        Map<String, String> response = new HashMap<>();
        response.put("checkoutUrl", sessionUrl);
        return response;
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
                session.getCustomerEmail() != null ? session.getCustomerDetails().getEmail() : "N/A"
        );
    }

    @PostMapping("/expire-session/{sessionId}")
    @ResponseBody
    public  Map<String, String> expireSession(@PathVariable String sessionId) throws StripeException {
        Session expiredSession = checkoutService.expireCheckoutSession(sessionId);

        Map<String, String> response = new HashMap<>();
        response.put("sessionId", expiredSession.getId());
        response.put("status", expiredSession.getStatus());
        return response;
    }


}

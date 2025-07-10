package com.example.demo.controller;

import com.example.demo.dto.OrderDto;
import com.example.demo.objects.Metadata;
import com.example.demo.request.OrderRequest;
import com.example.demo.response.CheckoutSessionResponse;
import com.example.demo.service.impl.CheckoutService;
import com.example.demo.service.impl.CountryService;
import com.example.demo.service.impl.PlayerService;
import com.example.demo.util.OrderDtoChecker;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/api/stripe")
@Controller
@CrossOrigin(origins = "*")
public class StripeController {

    private static final Logger log = LoggerFactory.getLogger(StripeController.class);

    @Autowired
    private final CheckoutService checkoutService;

    @Autowired
    private final PlayerService playerService;

    @Autowired
    private final CountryService countryService;

    public StripeController(CheckoutService checkoutService, PlayerService playerService, CountryService countryService) {
        this.checkoutService = checkoutService;
        this.playerService = playerService;
        this.countryService = countryService;
    }


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
    public ResponseEntity<?> createCheckoutSession(@Valid @RequestBody OrderRequest orderRequest) throws StripeException {
        OrderDtoChecker.validateOrderDto(orderRequest);

        List<Long> playerXIds = playerService.searchPlayerAndReturnId(orderRequest.getMetadata().getPlayerX());
        List<Long> playerYIds = playerService.searchPlayerAndReturnId(orderRequest.getMetadata().getPlayerY());
        List<Long> countriesIds = countryService.getCountryIds(orderRequest.getMetadata().getCountry());

        OrderDto orderDto = new OrderDto(
                orderRequest.getEmail(),
                orderRequest.getAmount(),
                orderRequest.getCurrency(),
                new Metadata(playerXIds, playerYIds, countriesIds)
        );

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

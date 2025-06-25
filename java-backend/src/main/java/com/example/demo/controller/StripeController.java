package com.example.demo.controller;


import com.example.demo.dto.ChargeRequest;
import com.example.demo.service.impl.StripeService;
import com.example.demo.types.Currency;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/product/v1")
@CrossOrigin(origins = "*")
public class StripeController {

    @Autowired
    private StripeService stripeService;


    @PostMapping("/charge")
    public String charge(ChargeRequest chargeRequest, Model model)
            throws StripeException {
        chargeRequest.setCurrency(Currency.EUR);
        Charge charge = stripeService.charge(chargeRequest);
        model.addAttribute("id", charge.getId());
        model.addAttribute("status", charge.getStatus());
        model.addAttribute("chargeId", charge.getId());
        model.addAttribute("balance_transaction", charge.getBalanceTransaction());
        return "result";
    }

    @ExceptionHandler(StripeException.class)
    public String handleError(Model model, StripeException ex) {
        model.addAttribute("error", ex.getMessage());
        return "result";
    }


//    @PostMapping("/checkout")
//    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody ProductRequest productRequest){
//
//        StripeResponse stripeResponse = stripeService.checkoutProduct(productRequest);
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body(stripeResponse);
//    }

}

package com.example.demo.dto;

import com.example.demo.types.Currency;

public class ChargeRequest {

    private Currency currency;
    private String stripeEmail;
    private String stripeToken;
    private int amount;


    public ChargeRequest(){}
    public ChargeRequest(Currency currency, String stripeEmail, String stripeToken, int amount) {
        this.currency = currency;
        this.stripeEmail = stripeEmail;
        this.stripeToken = stripeToken;
        this.amount = amount;
    }



    public Currency getCurrency() {return currency;}
    public void setCurrency(Currency currency) {this.currency = currency;}

    public String getStripeEmail() {return stripeEmail;}
    public void setStripeEmail(String stripeEmail) {this.stripeEmail = stripeEmail;}

    public String getStripeToken() {return stripeToken;}
    public void setStripeToken(String stripeToken) {this.stripeToken = stripeToken;}

    public int getAmount() {return amount;}
    public void setAmount(int amount) {this.amount = amount;}
}

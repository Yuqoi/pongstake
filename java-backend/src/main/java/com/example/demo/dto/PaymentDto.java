package com.example.demo.dto;

import com.example.demo.types.Currency;

public class PaymentDto {

    private String description;
    private int amount;
    private Currency currency;


    public PaymentDto() {}
    public PaymentDto(String description, int amount, Currency currency) {
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }
}

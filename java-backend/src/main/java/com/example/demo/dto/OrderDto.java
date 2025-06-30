package com.example.demo.dto;

import com.example.demo.model.Metadata;
import com.example.demo.types.Currency;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class OrderDto {

    @NotNull
    @Email
    private String email;

    @NotNull
    private Long amount;

    @NotNull
    private Currency currency;

    @NotNull
    private Metadata metadata;

    public OrderDto(){}
    public OrderDto(String email, Long amount, Currency currency, Metadata metadata) {
        this.email = email;
        this.amount = amount;
        this.currency = currency;
        this.metadata = metadata;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}

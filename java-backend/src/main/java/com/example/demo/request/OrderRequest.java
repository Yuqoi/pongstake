package com.example.demo.request;

import com.example.demo.types.Currency;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

public class OrderRequest {


    @Email
    @NotNull
    private String email;

    @NotNull
    private Long amount;

    @NotNull
    private Currency currency;

    @NotNull
    private MetadataRequest metadata;

    public OrderRequest(){}
    public OrderRequest(String email, Long amount, Currency currency, MetadataRequest metadata) {
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

    public MetadataRequest getMetadata() {
        return metadata;
    }
    public void setMetadata(MetadataRequest metadata) {
        this.metadata = metadata;
    }
}

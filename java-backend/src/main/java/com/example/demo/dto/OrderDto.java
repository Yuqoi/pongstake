package com.example.demo.dto;

import com.example.demo.objects.Metadata;
import com.example.demo.types.Currency;

import javax.validation.constraints.NotNull;

public class OrderDto {

    @NotNull
    private Long amount;

    @NotNull
    private Currency currency;

    @NotNull
    private Metadata metadata;

    public OrderDto(){}
    public OrderDto(Long amount, Currency currency, Metadata metadata) {
        this.amount = amount;
        this.currency = currency;
        this.metadata = metadata;
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

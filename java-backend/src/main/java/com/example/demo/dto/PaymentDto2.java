package com.example.demo.dto;

import com.example.demo.types.Currency;

import java.util.Date;

public class PaymentDto2 {

    private long idPayments;
    private String paymentStatus;
    private Date paymentDate;
    private String stripePaymentId;

    public static Currency fromString(String value) {
        return Currency.valueOf(value);
    }

    private String description;
    private int amount;
    private Currency currency;

    public PaymentDto2() {}
    public PaymentDto2(long idPayments, String paymentStatus, Date paymentDate, String stripePaymentId, String description, int amount, Currency currency) {
        this.idPayments = idPayments;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.stripePaymentId = stripePaymentId;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }

    public long getIdPayments() {
        return idPayments;
    }

    public void setIdPayments(long idPayments) {
        this.idPayments = idPayments;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStripePaymentId() {
        return stripePaymentId;
    }

    public void setStripePaymentId(String stripePaymentId) {
        this.stripePaymentId = stripePaymentId;
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

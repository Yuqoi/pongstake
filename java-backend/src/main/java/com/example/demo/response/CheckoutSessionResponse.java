package com.example.demo.response;


/**
 * TODO: Maybe change status variable to enum Status ?
 */
public class CheckoutSessionResponse {

    private String sessionId;
    private String paymentStatus;
    private long totalAmount;
    private String currency;
    private String status;
    private String customerEmail;

    public CheckoutSessionResponse() {}
    public CheckoutSessionResponse(String sessionId, String paymentStatus, long totalAmount, String currency, String status, String customerEmail) {
        this.sessionId = sessionId;
        this.paymentStatus = paymentStatus;
        this.totalAmount = totalAmount;
        this.currency = currency;
        this.status = status;
        this.customerEmail = customerEmail;
    }

    public String getSessionId() {
        return sessionId;
    }
    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public long getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(long totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }
    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }
}

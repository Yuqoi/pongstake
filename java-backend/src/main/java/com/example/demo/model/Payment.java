package com.example.demo.model;


import com.example.demo.types.Currency;
import jakarta.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "payments")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long idPayments;

    @Column(name = "status")
    @NotNull
    private String paymentStatus;

    @Column(name = "date")
    @NotNull
    private Date paymentDate;

    @Column(name = "stripe_payment_id")
    @NotNull
    private String stripePaymentId;

    @Column(name = "description")
    @NotNull
    private String description;

    @Column(name = "amount")
    @NotNull
    private int amount;


    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    @NotNull
    private Currency currency;

    public Payment() {}
    public Payment(long idPayments, String paymentStatus, Date paymentDate, String stripePaymentId, String description, int amount, Currency currency) {
        this.idPayments = idPayments;
        this.paymentStatus = paymentStatus;
        this.paymentDate = paymentDate;
        this.stripePaymentId = stripePaymentId;
        this.description = description;
        this.amount = amount;
        this.currency = currency;
    }

    public long getIdPayments() {return idPayments;}
    public void setIdPayments(long idPayments) {this.idPayments = idPayments;}

    public String getPaymentStatus() {return paymentStatus;}
    public void setPaymentStatus(String paymentStatus) {this.paymentStatus = paymentStatus;}

    public Date getPaymentDate() {return paymentDate;}
    public void setPaymentDate(Date paymentDate) {this.paymentDate = paymentDate;}

    public String getStripePaymentId() {return stripePaymentId;}
    public void setStripePaymentId(String stripePaymentId) {this.stripePaymentId = stripePaymentId;}

    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}

    public int getAmount() {return amount;}
    public void setAmount(int amount) {this.amount = amount;}

    public Currency getCurrency() {return currency;}
    public void setCurrency(Currency currency) {this.currency = currency;}
}

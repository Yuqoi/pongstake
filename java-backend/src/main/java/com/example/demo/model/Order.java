package com.example.demo.model;

import com.example.demo.types.Currency;
import com.example.demo.types.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.jpa.repository.Meta;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "payment_id")
    @NotNull
    private String paymentId;

    @Column(name = "date")
    @NotNull
    @DateTimeFormat
    private Date date;

    @Column(name = "email")
    @NotNull
    @Email
    private String email;

    @Column(name = "amount")
    @NotNull
    private Long amount;

    @Column(name = "total_price")
    @NotNull
    private Long price;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency")
    @NotNull
    private Currency currency;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    @NotNull
    private Status status;

    @Column(name = "metadata")
    @NotNull
    @JdbcTypeCode(SqlTypes.JSON)
    private Metadata metadata;

    public Order() {}
    public Order(Long id, String paymentId, Date date, String email, Long amount, Long price, Currency currency, Status status, Metadata metadata) {
        this.id = id;
        this.paymentId = paymentId;
        this.date = date;
        this.email = email;
        this.amount = amount;
        this.price = price;
        this.currency = currency;
        this.status = status;
        this.metadata = metadata;
    }

    public Long getPrice() {
        return price;
    }
    public void setPrice(Long price) {
        this.price = price;
    }

    public String getPaymentId() {
        return paymentId;
    }
    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
        this.date = date;
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

    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }

    public Metadata getMetadata() {
        return metadata;
    }
    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }
}

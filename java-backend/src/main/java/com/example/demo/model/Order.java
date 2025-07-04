package com.example.demo.model;

import com.example.demo.objects.Metadata;
import com.example.demo.types.Currency;
import com.example.demo.types.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@EntityListeners(AuditingEntityListener.class)
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
    @CreatedDate
    private LocalDateTime date;

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

    @NotNull
    @Value("false")
    @Column(name = "email_sent")
    private boolean emailSent;

    private Order() {}
    private Order(Long id, String paymentId, LocalDateTime date, String email, Long amount, Long price, Currency currency, Status status, Metadata metadata, boolean emailSent) {
        this.id = id;
        this.paymentId = paymentId;
        this.date = date;
        this.email = email;
        this.amount = amount;
        this.price = price;
        this.currency = currency;
        this.status = status;
        this.metadata = metadata;
        this.emailSent = emailSent;
    }

    public boolean isEmailSent() {
        return emailSent;
    }
    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
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

    public LocalDateTime getDate() {
        return date;
    }
    public void setDate(LocalDateTime date) {
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

    public static OrderBuilder builder() {
        return new OrderBuilder();
    }

    public static class OrderBuilder {

        private Long id;
        private String paymentId;
        private LocalDateTime date;
        private String email;
        private Long amount;
        private Long price;
        private Currency currency;
        private Status status;
        private Metadata metadata;
        private boolean emailSent;

        public OrderBuilder id(Long id){
            this.id = id;
            return this;
        }

        public OrderBuilder emailSent(boolean emailSent){
            this.emailSent = emailSent;
            return this;
        }

        public OrderBuilder paymentId(String paymentId){
            this.paymentId = paymentId;
            return this;
        }

        public OrderBuilder date(LocalDateTime date){
            this.date = date;
            return this;
        }

        public OrderBuilder email(String email){
            this.email = email;
            return this;
        }

        public OrderBuilder amount(Long amount){
            this.amount = amount;
            return this;
        }
        public OrderBuilder price(Long price){
            this.price = price;
            return this;
        }

        public OrderBuilder currency(Currency currency){
            this.currency = currency;
            return this;
        }

        public OrderBuilder status(Status status){
            this.status = status;
            return this;
        }
        public OrderBuilder metadata(Metadata metadata){
            this.metadata = metadata;
            return this;
        }

        public Order build(){
            return new Order(id,paymentId,date,email,amount,price,currency,status,metadata, emailSent);
        }
    }
}

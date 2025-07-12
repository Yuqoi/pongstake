package com.pongstake.notifications.model;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Value;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "predictions")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @OneToOne
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Column(name = "results", columnDefinition = "integer[]")
    private Integer[] result;

    @NotNull
    @Value("false")
    @Column(name = "email_sent")
    private boolean emailSent;

    public Prediction() {}
    public Prediction(Order order, Integer[] result, boolean emailSent) {
        this.order = order;
        this.result = result;
        this.emailSent = emailSent;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Order getOrder() {return order;}
    public void setOrder(Order order) {this.order = order;}

    public Integer[] getResult() {
        return result;
    }
    public void setResult(Integer[] result) {
        this.result = result;
    }

    public boolean isEmailSent() {
        return emailSent;
    }
    public void setEmailSent(boolean emailSent) {
        this.emailSent = emailSent;
    }

}


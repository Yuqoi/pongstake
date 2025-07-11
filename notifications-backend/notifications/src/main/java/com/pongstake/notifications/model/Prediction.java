package com.pongstake.notifications.model;

import com.pongstake.notifications.type.IntArrayType;
import jakarta.persistence.*;
import org.hibernate.annotations.Type;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "predictions")
public class Prediction {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @JoinColumn(name = "order_id", referencedColumnName = "id", unique = true)
    @NotNull
    private Long orderId;


    @Column(name = "results", columnDefinition = "integer[]")
    private Integer[] result;

    @NotNull
    @Value("false")
    @Column(name = "email_sent")
    private boolean emailSent;

    public Prediction() {}
    public Prediction(long id, Long orderId, Integer[] result, boolean emailSent) {
        this.id = id;
        this.orderId = orderId;
        this.result = result;
        this.emailSent = emailSent;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }
    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

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


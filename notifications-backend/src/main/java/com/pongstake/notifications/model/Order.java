package com.pongstake.notifications.model;


import jakarta.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    @NotNull
    @Email
    private String email;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.LAZY, optional = false)
    private Prediction prediction;

    public Order() { }
    public Order(Long id, String email, Prediction prediction) {
        this.id = id;
        this.email = email;
        this.prediction = prediction;
    }

    public Prediction getPrediction() {
        return prediction;
    }
    public void setPrediction(Prediction prediction) {
        this.prediction = prediction;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
}

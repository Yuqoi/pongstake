package com.example.demo.model;


import com.example.demo.types.Currency;
import jakarta.persistence.*;

import javax.validation.constraints.NotNull;

/**
 * TODO: Probably change later to record if it would be possible by any chance
 */

@Entity
@Table(name = "prices")
public class Price {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "name")
    private Currency currency;

    @NotNull
    @Column(name = "price")
    private Long price;

    public Price() {}
    public Price(Currency currency, Long price) {
        this.currency = currency;
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Price{" +
                "currency=" + currency +
                ", price=" + price +
                '}';
    }
}

package com.example.demo.helpers;

import com.example.demo.dto.OrderDto;

import com.example.demo.model.Price;
import com.example.demo.repository.PriceRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class CalculateCost {


    private static PriceRepository priceRepository;

    public CalculateCost(PriceRepository priceRepository){
        CalculateCost.priceRepository = priceRepository;
    }

    public static Long calculateCost(OrderDto order){
        Optional<Price> price = priceRepository.findById(order.getCurrency());
        return price.map(value -> value.getPrice() * order.getAmount()).orElse(0L);
    }


}

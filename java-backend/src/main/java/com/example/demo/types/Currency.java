package com.example.demo.types;

import com.example.demo.deserializer.CurrencyDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(using = CurrencyDeserializer.class)
public enum Currency {
    EUR,
    USD
}

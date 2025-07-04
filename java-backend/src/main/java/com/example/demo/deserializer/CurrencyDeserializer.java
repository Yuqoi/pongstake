package com.example.demo.deserializer;

import com.example.demo.exceptions.InvalidEnumTypeException;
import com.example.demo.types.Currency;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import java.io.IOException;

public class CurrencyDeserializer extends StdDeserializer<Currency> {

    protected CurrencyDeserializer() {
        super(Currency.class);
    }

    @Override
    public Currency deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException {
        try{
            String gottenValue = jsonParser.getText().toUpperCase();
            return Currency.valueOf(gottenValue);
        }catch (IllegalArgumentException e){
            throw new InvalidEnumTypeException("Wrong enum try EUR or USD");
        }

    }
}

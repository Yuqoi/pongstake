package com.example.demo.util;

import com.example.demo.dto.OrderDto;
import com.example.demo.exceptions.InvalidFieldsException;
import com.example.demo.objects.Metadata;
import com.example.demo.types.Currency;

public class OrderDtoChecker {

    /**
     * This class will check if given amount is equal to both playerX and playerY and country size
     * for ex playerX: ['x'], playerY: ['y'], country: ['z'] == 1 and amount == 1 so True
     */


    public static void validateOrderDto(OrderDto orderDto){
        checkEmail(orderDto.getEmail());
        checkAmount(orderDto.getAmount());
        checkMetadataElements(orderDto.getMetadata());
        checkIfElementsAreEqual(orderDto.getMetadata(), orderDto.getAmount());
    }

    private static void checkEmail(String email){
        if (email == null || email.isBlank()){
            throw new InvalidFieldsException("Email is required");
        }
    }
    private static void checkAmount(Long amount){
        if (amount <= 0){
            throw new InvalidFieldsException("Proper Amount is required");
        }
    }
    private static void checkMetadataElements(Metadata metadata){
        if (metadata == null){
            throw new InvalidFieldsException("Metadata is null");
        }
        if (metadata.getPlayerX() == null || metadata.getPlayerX().isEmpty()){
            throw new InvalidFieldsException("PlayerX is empty or null");
        }
        if (metadata.getPlayerY() == null || metadata.getPlayerY().isEmpty()){
            throw new InvalidFieldsException("PlayerY is empty or null");
        }
        if (metadata.getCountry() == null || metadata.getCountry().isEmpty()){
            throw new InvalidFieldsException("Country is empty or null");
        }
    }
    private static void checkIfElementsAreEqual(Metadata metadata, long amount){

    }

}

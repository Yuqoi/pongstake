package com.example.demo.controller;


import com.example.demo.dto.OrderDto;
import com.example.demo.model.Metadata;
import com.example.demo.model.Order;
import com.example.demo.repository.OrderRepository;
import com.example.demo.types.Currency;
import com.example.demo.types.Status;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class StripeController {

    @Autowired
    private OrderRepository orderRepository;


    @PostMapping("/order")
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto){

        orderRepository.save(new Order("ada", new Date(System.currentTimeMillis()), "aleksander@gmail.com", 5L, Currency.EUR, Status.WAITING,
                new Metadata(List.of("olek"), List.of("pierd"), List.of("china"))));

        return ResponseEntity.status(HttpStatus.OK).body("git");
    }



}

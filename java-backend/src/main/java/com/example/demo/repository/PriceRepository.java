package com.example.demo.repository;

import com.example.demo.model.Price;
import com.example.demo.types.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price, Currency> { }
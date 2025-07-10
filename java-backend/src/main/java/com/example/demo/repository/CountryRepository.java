package com.example.demo.repository;

import com.example.demo.model.Country;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

    @Query("SELECT c.countryIndex FROM Country c WHERE LOWER(c.countryName) = LOWER(:countryName)")
    Optional<Long> findIndexByCountryName(String countryName);

}

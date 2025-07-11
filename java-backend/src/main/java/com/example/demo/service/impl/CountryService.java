package com.example.demo.service.impl;


import com.example.demo.model.Country;
import com.example.demo.repository.CountryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class CountryService {

    private static final Logger log = LoggerFactory.getLogger(CountryService.class);
    @Autowired
    private final CountryRepository countryRepository;

    public CountryService(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    public List<Long> getCountryIds(List<String> countryList){
        if (countryList.isEmpty()){
            return Collections.emptyList();
        }
        return countryList.stream()
                .map(name -> countryRepository.findIndexByCountryName(name).orElse(-1L))
                .toList();
    }

}

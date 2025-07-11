package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import javax.validation.constraints.NotNull;

@Entity
@Table(name = "countries")
public class Country {

    @Id
    @Column(name = "id")
    private Long id;

    @NotNull
    @Column(name = "index")
    private Long countryIndex;

    @Column(name = "country")
    private String countryName;

    @Column(name = "code")
    private String code;


    public Country(){}
    public Country(Long id, Long countryIndex, String countryName, String code) {
        this.id = id;
        this.countryIndex = countryIndex;
        this.countryName = countryName;
        this.code = code;
    }

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public Long getCountryIndex() {
        return countryIndex;
    }
    public void setCountryIndex(Long countryIndex) {
        this.countryIndex = countryIndex;
    }

    public String getCountryName() {
        return countryName;
    }
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
}

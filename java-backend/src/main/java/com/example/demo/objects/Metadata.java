package com.example.demo.objects;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Metadata {

    private List<Long> playerX;
    private List<Long> playerY;
    private List<Long> country;

    public Metadata(List<Long> playerX, List<Long> playerY, List<Long> country) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.country = country;
    }

    public List<Long> getPlayerX() {
        return playerX;
    }
    public void setPlayerX(List<Long> playerX) {
        this.playerX = playerX;
    }

    public List<Long> getPlayerY() {
        return playerY;
    }
    public void setPlayerY(List<Long> playerY) {
        this.playerY = playerY;
    }

    public List<Long> getCountry() {
        return country;
    }
    public void setCountry(List<Long> country) {
        this.country = country;
    }
}

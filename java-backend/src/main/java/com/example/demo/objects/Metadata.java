package com.example.demo.objects;

import java.util.List;

public class Metadata {

    private List<String> playerX;
    private List<String> playerY;
    private List<String> country;

    public Metadata(List<String> playerX, List<String> playerY, List<String> country) {
        this.playerX = playerX;
        this.playerY = playerY;
        this.country = country;
    }

    public List<String> getPlayerX() {
        return playerX;
    }
    public void setPlayerX(List<String> playerX) {
        this.playerX = playerX;
    }

    public List<String> getPlayerY() {
        return playerY;
    }
    public void setPlayerY(List<String> playerY) {
        this.playerY = playerY;
    }

    public List<String> getCountry() {
        return country;
    }
    public void setCountry(List<String> country) {
        this.country = country;
    }
}

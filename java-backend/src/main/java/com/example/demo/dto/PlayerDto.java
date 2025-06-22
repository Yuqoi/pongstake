package com.example.demo.dto;

public class PlayerDto {

    private String playerName;

    public PlayerDto(String playerName) {
        this.playerName = playerName;
    }

    public PlayerDto(){}

    public String getPlayerName() {return playerName;}
    public void setPlayerName(String playerName) {this.playerName = playerName;}
}

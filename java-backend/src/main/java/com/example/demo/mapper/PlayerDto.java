package com.example.demo.mapper;

public class PlayerDto {

    private Long id;
    private String playerName;

    public PlayerDto(Long id, String playerName) {
        this.id = id;
        this.playerName = playerName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}

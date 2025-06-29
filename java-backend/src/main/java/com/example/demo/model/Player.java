package com.example.demo.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "players")
public class Player {

    @Id
    @Column(name = "player_id")
    private Long id;

    @Column(name = "player_name")
    private String playerName;

    private Player() { }
    public Player(Long id, String playerName) {
        this.id = id;
        this.playerName = playerName;
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getPlayerName() {return playerName;}
    public void setPlayerName(String playerName) {this.playerName = playerName;}

    @Override
    public String toString() {
        return String.format("#%s, %s", id, playerName);
    }
}




package com.example.demo.mapper;


import com.example.demo.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    public PlayerDto toDto(Player player){
        return new PlayerDto(player.getId(), player.getPlayerName());
    }
    public Player toPlayer(PlayerDto player){
        return new Player(player.getId(), player.getPlayerName());
    }
}

package com.example.demo.mapper;


import com.example.demo.dto.PlayerDto;
import com.example.demo.model.Player;
import org.springframework.stereotype.Component;

@Component
public class PlayerMapper {
    public PlayerDto toDto(Player player){
        return new PlayerDto(player.getPlayerName());
    }
}

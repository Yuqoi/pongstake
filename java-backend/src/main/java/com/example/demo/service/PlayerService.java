package com.example.demo.service;


import com.example.demo.mapper.PlayerMapper;
import com.example.demo.model.Player;
import com.example.demo.mapper.PlayerDto;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerService {

    @Autowired
    private final PlayerRepository playerRepository;

    @Autowired
    private PlayerMapper playerMapper;


    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    public List<PlayerDto> getAllPLayers(){
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream()
                .map(player -> playerMapper.toDto(player))
                .collect(Collectors.toList());
    }

    public List<PlayerDto> searchPlayers(String name){
        if (name == null || name.isBlank()){
            return null;
        }
        Optional<List<Player>> optionalPlayers = playerRepository.findTop10ByPlayerNameContainingIgnoreCaseOrderByPlayerName(name);
        return optionalPlayers.map(players -> players.stream()
                .map(player -> playerMapper.toDto(player))
                .collect(Collectors.toList())).orElse(null);

    }

}

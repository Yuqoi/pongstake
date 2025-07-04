package com.example.demo.service.impl;


import com.example.demo.mapper.PlayerMapper;
import com.example.demo.model.Player;
import com.example.demo.dto.PlayerDto;
import com.example.demo.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerService {

    @Autowired
    private final PlayerRepository playerRepository;

    @Autowired
    private final PlayerMapper playerMapper;

    public PlayerService(PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }

    public List<PlayerDto> getAllPLayers(){
        List<Player> playerList = playerRepository.findAll();
        return playerList.stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList());
    }


    @Cacheable(value = "playerSearchCache", key = "#name")
    public List<PlayerDto> searchPlayers(String name){
        if (name == null || name.isBlank()){
            return Collections.emptyList();
        }

        Optional<List<Player>> optionalPlayers =
                playerRepository.findTop10ByPlayerNameContainingIgnoreCaseOrderByPlayerName(name);

        return optionalPlayers.map(players -> players.stream()
                .map(playerMapper::toDto)
                .collect(Collectors.toList())).orElse(null);

    }

}

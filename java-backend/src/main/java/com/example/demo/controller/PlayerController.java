package com.example.demo.controller;

import com.example.demo.dto.PlayerDto;
import com.example.demo.service.impl.PlayerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayerController {


    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping(value = "/player", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<PlayerDto>> getAllPlayers(){
        return new ResponseEntity<>(playerService.getAllPLayers(), HttpStatus.OK);
    }

    @GetMapping(value = "/player/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getPlayer(@PathVariable String name){
        List<PlayerDto> playersFound = playerService.searchPlayers(name);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(playersFound);
    }

}

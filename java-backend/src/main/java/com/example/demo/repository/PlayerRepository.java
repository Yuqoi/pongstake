package com.example.demo.repository;

import com.example.demo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<List<Player>> findTop10ByPlayerNameContainingIgnoreCaseOrderByPlayerName(String name);

}

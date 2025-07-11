package com.example.demo.repository;

import com.example.demo.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface PlayerRepository extends JpaRepository<Player, Long> {

    Optional<List<Player>> findTop10ByPlayerNameContainingIgnoreCaseOrderByPlayerName(String name);

    @Query("SELECT c.id FROM Player c WHERE LOWER(c.playerName) = LOWER(:name)")
    Optional<Long> findIdByPlayerNameContainingIgnoreCase(String name);

}

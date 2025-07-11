package com.pongstake.notifications.repository;

import com.pongstake.notifications.model.Order;
import com.pongstake.notifications.model.Prediction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PredictionRepository extends JpaRepository<Prediction, Long> { }

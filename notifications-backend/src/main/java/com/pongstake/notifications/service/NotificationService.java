package com.pongstake.notifications.service;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.pongstake.notifications.model.Order;
import com.pongstake.notifications.model.Prediction;
import com.pongstake.notifications.repository.OrderRepository;
import com.pongstake.notifications.repository.PredictionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.StreamSupport;
@Service
public class NotificationService {

    private static final Logger log = LoggerFactory.getLogger(NotificationService.class);
    private static final ObjectMapper mapper = new ObjectMapper();

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PredictionRepository predictionRepository;

    @KafkaListener(topics = "predictions_made", groupId = "pongstake")
    public void retrieveResults(String gottenResult){
        try {
            JsonNode actualObj = mapper.readTree(gottenResult);
            final long orderId = actualObj.get("orderId").asLong();

            ArrayNode arrayNodeList = (ArrayNode) actualObj.get("result");

            ObjectReader reader = mapper.readerFor(new TypeReference<Integer[]>() { });
            Integer[] convertedList = reader.readValue(arrayNodeList);

            Optional<Order> optionalOrder = orderRepository.findById(orderId);

            if (optionalOrder.isPresent()){
                Order order = optionalOrder.get();
                Prediction prediction = new Prediction();

                prediction.setResult(convertedList);
                prediction.setOrder(order);

                predictionRepository.saveAndFlush(prediction);

                log.info("Everything saved for: {} id", prediction.getId());
            }else{
                log.warn("Couldn't find an order: {}", orderId);
            }
        } catch (JsonProcessingException e) {
            log.error("ERROR WITH RETRIEVING JSON: {}", e.getMessage());
        } catch (IOException e) {
            log.error("ERROR WITH CONVERTING TO ARRAY: {}", e.getMessage());
        }

    }

}

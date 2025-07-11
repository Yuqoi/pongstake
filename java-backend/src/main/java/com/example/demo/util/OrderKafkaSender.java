package com.example.demo.util;


import com.example.demo.config.KafkaConfig;
import com.example.demo.model.Order;
import com.example.demo.objects.KafkaProducerMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Recover;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderKafkaSender {

    private static final Logger log = LoggerFactory.getLogger(OrderKafkaSender.class);

    @Autowired
    private KafkaTemplate<String, KafkaProducerMessage> orderKafkaTemplate;

    @Value("${spring.kafka.topic.name}")
    private String topicName;

    @Value("${spring.kafka.enabled}")
    private boolean isKafkaEnabled;

    @Retryable(value = KafkaException.class, maxAttempts = 3, backoff = @Backoff(delay = 3000))
    public String send(KafkaProducerMessage kafkaProducerMessage) {
        log.info("sending data='{}' to topic='{}'", kafkaProducerMessage, topicName);
        String key = UUID.randomUUID().toString();
        if (isKafkaEnabled) {
            orderKafkaTemplate.send(topicName, key, kafkaProducerMessage);
            return "data sent successfully";
        }
        return "kafka is disabled in application";
    }

    @Recover
    public void recoverKafkaMessage(KafkaException exception) {
        log.error("sending data to error queue with message: {}", exception.getMessage());
    }


}

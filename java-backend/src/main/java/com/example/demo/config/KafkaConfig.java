package com.example.demo.config;


import com.example.demo.model.Order;
import com.example.demo.objects.KafkaProducerMessage;
import jakarta.annotation.PostConstruct;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.retry.annotation.EnableRetry;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
@EnableRetry
//@ConditionalOnProperty(prefix = "app", name = "kafka.enabled", havingValue = "true")
public class KafkaConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String kafkaHost;

    private static final Map<String, Object> DEFAULT_PRODUCER_CONFIG_MAP = new HashMap<>();

    @PostConstruct
    public void configureKafkaProperties(){
        DEFAULT_PRODUCER_CONFIG_MAP.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost);
        DEFAULT_PRODUCER_CONFIG_MAP.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        DEFAULT_PRODUCER_CONFIG_MAP.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);
    }

    @Bean
    public KafkaTemplate<String, KafkaProducerMessage> kafkaTemplate() {
        return new KafkaTemplate<>(orderProducerFactory());
    }

    @Bean
    public ProducerFactory<String, KafkaProducerMessage> orderProducerFactory() {
        JsonSerializer<KafkaProducerMessage> userJsonSerializer = new JsonSerializer<KafkaProducerMessage>();
        userJsonSerializer.setAddTypeInfo(false);
        return new DefaultKafkaProducerFactory<>(DEFAULT_PRODUCER_CONFIG_MAP, new StringSerializer(), userJsonSerializer);
    }


}

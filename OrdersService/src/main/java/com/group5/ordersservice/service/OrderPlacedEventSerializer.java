package com.group5.ordersservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderPlacedEventSerializer implements Serializer<OrderPlacedEvent> {

    private final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = LoggerFactory.getLogger(OrderPlacedEventSerializer.class);

    @Override
    public byte[] serialize(String topic, OrderPlacedEvent data) {
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (JsonProcessingException e) {
            logger.error("Error serializing OrderPlacedEvent", e);
            throw new RuntimeException("Error serializing OrderPlacedEvent", e);
        }
    }

    @Override
    public void close() {
        // Optional: Clean up resources if needed
    }
}

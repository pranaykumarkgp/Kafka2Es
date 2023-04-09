package com.kafka2es.kafka.serializer;


import com.kafka2es.dto.Product;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Map;

public class ProductDeserializer implements Deserializer<Product> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public void configure(Map<String, ?> configs, boolean isKey) {}

    @Override
    public Product deserialize(String topic, byte[] data) {
        try {
            return objectMapper.readValue(data, Product.class);
        } catch (Exception e) {
            throw new RuntimeException("Error deserializing Person", e);
        }
    }

    @Override
    public void close() {}
}

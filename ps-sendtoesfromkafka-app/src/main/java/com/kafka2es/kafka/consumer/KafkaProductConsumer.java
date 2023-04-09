package com.kafka2es.kafka.consumer;


import com.kafka2es.dto.Product;
import com.kafka2es.elasticsearch.service.ElasticSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class KafkaProductConsumer {

    private Logger logger = LoggerFactory.getLogger(KafkaProductConsumer.class);

    @Autowired
    ElasticSearchService elasticSearchService;

    @KafkaListener(
            topics = "product-topic",
            groupId = "101"
    )

    public void listener(String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(data, Product.class);
        logger.info("KafkaProductEvent received with productId: {}, productName: {}, colour: {}, price: {}", product.getId(), product.getName(), product.getColour(), product.getPrice());
        try {
            elasticSearchService.putDocument(product);
            logger.info("Inserting Document into Elastic Search");
        }catch (Exception e){
            logger.error("Exception Occurred while inserting document into elastic search");
        }
    }


}

package com.kafka2es.kafka.consumer;


import com.kafka2es.dto.Product;
import com.kafka2es.elasticsearch.service.ElasticSearchService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.io.IOException;

@Component
public class KafkaProductConsumer {

    @Autowired
    ElasticSearchService elasticSearchService;

    @KafkaListener(
            topics = "product-topic",
            groupId = "101"
    )

    public void listener(String data) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Product product = mapper.readValue(data, Product.class);
        System.out.println("listener received, name: " + product.getName() + ", colour: " + product.getColour() + ", price: " + product.getPrice() + ", id:" + product.getId());
        try {
            elasticSearchService.putDocument(product);
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }


}

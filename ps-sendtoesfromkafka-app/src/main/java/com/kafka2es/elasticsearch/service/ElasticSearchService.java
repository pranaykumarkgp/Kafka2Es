package com.kafka2es.elasticsearch.service;

import com.kafka2es.dto.Product;
import com.kafka2es.elasticsearch.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Slf4j
@Service
public class ElasticSearchService {

    @Autowired
    private ProductRepository productRepository;

    RestClient restClient = RestClient.builder(new HttpHost("127.0.0.1", 9200, "http")).build();

    Request request = new Request("POST", "/productindex/_doc");

    public Iterable<Product> findAll(){
        return productRepository.findAll();
    }

    public ResponseEntity putDocument(Product product){
        try{
            log.info("Inserting document :{} into elastic search", product.toString());
            request.setJsonEntity(product.toString());
            restClient.performRequest(request);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            log.error("Exception Thrown while adding product, exception: {}", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}

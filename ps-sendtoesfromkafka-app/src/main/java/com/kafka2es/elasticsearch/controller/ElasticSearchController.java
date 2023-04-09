package com.kafka2es.elasticsearch.controller;

import com.kafka2es.dto.Product;
import com.kafka2es.elasticsearch.service.ElasticSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/products")
public class ElasticSearchController {

    @Autowired
    private ElasticSearchService elasticSearchService;

    @PostMapping
    public ResponseEntity putDocument(){
        Product product = new Product();
        product.setName("Nike");
        product.setColour("pitch black");
        product.setPrice(4500);
        try{
            elasticSearchService.putDocument(product);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e){
            System.out.println("Exception Thrown while adding product");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/getElasticUserFromKafka")
    public Iterable<Product> findAllUser() {
        return elasticSearchService.findAll();
    }


}

package com.kafka2es;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaToElasticSearch {

    public static void main(String[] args) {
        SpringApplication.run(KafkaToElasticSearch.class, args);
    }

}

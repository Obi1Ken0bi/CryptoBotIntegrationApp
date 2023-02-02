package com.example.cryptobotintegrationapp.integration.cryptobot;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Value("${crypto-pay.api.token}")
    private String token;
    @Value("${crypto-pay.api.url}")
    private String baseUrl;

    @Bean
    RestTemplate cryptoRestTemplate(RestTemplateBuilder restTemplateBuilder) {
        return restTemplateBuilder.
                defaultHeader("Crypto-Pay-API-Token", token)
                .rootUri(baseUrl)
                .build();
    }

    @Bean
    ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.registerModule(new JavaTimeModule());


        return objectMapper;
    }
}

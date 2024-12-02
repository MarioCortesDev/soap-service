package com.pokemon.soap_service.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@TestConfiguration
public class TestConfig {

    @Bean
    @Primary
    public WebClient webClient() {
        return WebClient.builder()
            .baseUrl("https://pokeapi.co/api/v2")
            .build();
    }
}
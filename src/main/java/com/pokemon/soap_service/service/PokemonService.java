package com.pokemon.soap_service.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.pokemon.soap_service.model.PokemonResponse;
import com.pokemon.soap_service.model.RequestLog;
import com.pokemon.soap_service.repository.RequestLogRepository;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class PokemonService {
    private final WebClient webClient;
    private final RequestLogRepository requestLogRepository;

    public PokemonService(RequestLogRepository requestLogRepository) {
        this.webClient = WebClient.create("https://pokeapi.co/api/v2");
        this.requestLogRepository = requestLogRepository;
    }

    public Mono<PokemonResponse> getPokemonByName(String name, String clientIp) {
        log.info("Fetching Pokemon data for {}:", name);
        long startTime = System.currentTimeMillis();
        
        RequestLog requestLog = new RequestLog();
        requestLog.setOriginIp(clientIp);
        requestLog.setRequestDate(LocalDateTime.now());
        requestLog.setExecutedMethod("getPokemonByName");
        requestLog.setRequest(name);
        
        return webClient.get()
                .uri("/pokemon/" + name.toLowerCase())
                .retrieve()
                .bodyToMono(PokemonResponse.class)
                .doOnSuccess(response -> {
                    requestLog.setRequestDuration(System.currentTimeMillis() - startTime);
                    requestLog.setResponse(response.toString());
                    requestLogRepository.save(requestLog);
                    log.info("Successfully retrieved Pokemon data for: {}", name);
                })
                .doOnError(error -> log.error("Error retieving Pokemon data for: {}", name, error));
    }

    public void logRequest(RequestLog requestLog) {
        requestLogRepository.save(requestLog);
        log.info("Request logged: {}", requestLog);
    }
}

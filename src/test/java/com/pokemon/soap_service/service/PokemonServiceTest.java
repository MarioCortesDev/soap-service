package com.pokemon.soap_service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pokemon.soap_service.model.RequestLog;
import com.pokemon.soap_service.repository.RequestLogRepository;

import reactor.test.StepVerifier;


@SpringBootTest
class PokemonServiceTest {
    private static final String TEST_IP = "127.0.0.1";

    @Autowired
    private PokemonService pokemonService;

    @Test
    void getPokemonByName_ShouldReturnPokemon() {
        StepVerifier.create(pokemonService.getPokemonByName("pikachu", TEST_IP))
                .expectNextMatches(pokemon -> 
                    pokemon.getName().equals("pikachu") &&
                    pokemon.getId() > 0 &&
                    !pokemon.getAbilities().isEmpty()
                )
                .verifyComplete();
    }

     @Test
    void getPokemonByName_ShouldLogRequest(@Autowired RequestLogRepository logRepository) {
        // Given
        String pokemonName = "pikachu";

        // When
        pokemonService.getPokemonByName(pokemonName, TEST_IP).block();

        // Then
        List<RequestLog> logs = logRepository.findAll();
        assertFalse(logs.isEmpty());
        RequestLog lastLog = logs.get(logs.size() - 1);
        assertEquals(TEST_IP, lastLog.getOriginIp());
        assertEquals(pokemonName, lastLog.getRequest());
        assertNotNull(lastLog.getRequestDate());
        assertNotNull(lastLog.getRequestDuration());
    }
}
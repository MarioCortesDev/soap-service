package com.pokemon.soap_service.cucumber;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import io.cucumber.spring.CucumberContextConfiguration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.pokemon.soap_service.model.PokemonResponse;
import com.pokemon.soap_service.model.RequestLog;
import com.pokemon.soap_service.repository.RequestLogRepository;
import com.pokemon.soap_service.service.PokemonService;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.List;
import java.util.Map;

@CucumberContextConfiguration
@SpringBootTest
public class PokemonStepDefinitions {
    private static final String TEST_IP = "127.0.0.1";
    
    @Autowired
    private PokemonService pokemonService;
    
    @Autowired
    private RequestLogRepository requestLogRepository;
    
    private String pokemonName;
    private PokemonResponse pokemonResponse;
    private Exception thrownException;
    
    @Given("a pokemon name {string}")
    public void givenPokemonName(String name) {
        this.pokemonName = name;
    }

    @When("I request the pokemon abilities")
    public void whenRequestAbilities() {
        try {
            pokemonResponse = pokemonService.getPokemonByName(pokemonName, TEST_IP).block();
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("I request the pokemon base experience")
    public void whenRequestBaseExperience() {
        try {
            pokemonResponse = pokemonService.getPokemonByName(pokemonName, TEST_IP).block();
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("I request the pokemon held items")
    public void whenRequestHeldItems() {
        try {
            pokemonResponse = pokemonService.getPokemonByName(pokemonName, TEST_IP).block();
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("I request the pokemon ID")
    public void whenRequestId() {
        try {
            pokemonResponse = pokemonService.getPokemonByName(pokemonName, TEST_IP).block();
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("I request the pokemon name")
    public void whenRequestName() {
        try {
            pokemonResponse = pokemonService.getPokemonByName(pokemonName, TEST_IP).block();
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("I request the pokemon location encounters")
    public void whenRequestLocationEncounters() {
        try {
            pokemonResponse = pokemonService.getPokemonByName(pokemonName, TEST_IP).block();
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @When("I make any pokemon request")
    public void whenMakeAnyRequest() {
        try {
            pokemonResponse = pokemonService.getPokemonByName(pokemonName, TEST_IP)
                .block(Duration.ofSeconds(10));
        } catch (Exception e) {
            thrownException = e;
        }
    }

    @Then("I should receive a list of abilities")
    public void thenVerifyAbilities() {
        assertNotNull(pokemonResponse);
        assertNotNull(pokemonResponse.getAbilities());
        assertFalse(pokemonResponse.getAbilities().isEmpty());
    }

    @Then("I should receive a base experience value")
    public void thenVerifyBaseExperience() {
        assertNotNull(pokemonResponse);
        assertNotNull(pokemonResponse.getBaseExperience());
        assertTrue(pokemonResponse.getBaseExperience() > 0);
    }

    @Then("I should receive a list of held items")
    public void thenVerifyHeldItems() {
        assertNotNull(pokemonResponse);
        assertNotNull(pokemonResponse.getHeldItems());
    }

    @Then("I should receive a valid ID")
    public void thenVerifyId() {
        assertNotNull(pokemonResponse);
        assertNotNull(pokemonResponse.getId());
        assertTrue(pokemonResponse.getId() > 0);
    }

    @Then("I should receive the normalized name")
    public void thenVerifyName() {
        assertNotNull(pokemonResponse);
        assertNotNull(pokemonResponse.getName());
        assertEquals(pokemonName.toLowerCase(), pokemonResponse.getName().toLowerCase());
    }

    @Then("I should receive the location encounters URL")
    public void thenVerifyLocationEncounters() {
        assertNotNull(pokemonResponse, "Pokemon response should not be null");
        assertNotNull(pokemonResponse.getLocationAreaEncounters(), 
            "Location encounters URL should not be null");
        assertTrue(pokemonResponse.getLocationAreaEncounters()
            .contains("/pokemon/"), "URL should contain '/pokemon/'");
    }

    @Then("the request should be logged in database")
    public void thenVerifyLogging() {
        List<RequestLog> logs = requestLogRepository.findAll();
        
        // Cambiamos la aserción
        assertTrue(!logs.isEmpty(), "No logs found in database");
        
        if (!logs.isEmpty()) {
            RequestLog lastLog = logs.get(logs.size() - 1);
            
            assertAll("Log validations",
                () -> assertNotNull(lastLog.getOriginIp(), "IP should not be null"),
                () -> assertNotNull(lastLog.getRequestDate(), "Request date should not be null"),
                () -> assertNotNull(lastLog.getExecutedMethod(), "Method should not be null"),
                () -> assertNotNull(lastLog.getRequestDuration(), "Duration should not be null"),
                () -> assertNotNull(lastLog.getRequest(), "Request should not be null"),
                () -> assertNotNull(lastLog.getResponse(), "Response should not be null")
            );
        }
    }

    @Then("the following data should be logged in database:")
public void thenVerifySpecificLogging(DataTable dataTable) {
    List<RequestLog> logs = requestLogRepository.findAll();
    assertTrue(!logs.isEmpty(), "No logs found in database");
    
    RequestLog lastLog = logs.get(logs.size() - 1);

    // Convierte la DataTable a un Map pero saltando la primera fila (headers)
    List<Map<String, String>> rows = dataTable.asMaps();
    for (Map<String, String> row : rows) {
        String field = row.get("Field");
        switch (field) {
            case "IP":
                assertNotNull(lastLog.getOriginIp(), "IP should not be null");
                break;
            case "RequestDate":
                assertNotNull(lastLog.getRequestDate(), "RequestDate should not be null");
                break;
            case "Method":
                assertNotNull(lastLog.getExecutedMethod(), "Method should not be null");
                break;
            case "Duration":
                assertNotNull(lastLog.getRequestDuration(), "Duration should not be null");
                break;
            case "Request":
                assertNotNull(lastLog.getRequest(), "Request should not be null");
                break;
            case "Response":
                assertNotNull(lastLog.getResponse(), "Response should not be null");
                break;
            default:
                fail("Unknown field: " + field);
        }
    }
}

    @Then("I should receive an error")
    public void thenVerifyError() {
        assertNotNull(thrownException);
    }
}
package com.pokemon.soap_service.endpoint;

import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.pokemon.soap_service.model.PokemonResponse;
import com.pokemon.soap_service.service.PokemonService;

import com.pokemon.soap.*;

import jakarta.servlet.http.HttpServletRequest;

@Endpoint
public class PokemonEndPoint {
    
    private static final String NAMESPACE_URI = "http://pokemon.com/soap";
    private final PokemonService pokemonService;
    private final HttpServletRequest requestServlet;

    public PokemonEndPoint(PokemonService pokemonService, HttpServletRequest requestServlet) {
        this.pokemonService = pokemonService;
        this.requestServlet = requestServlet;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonAbilitiesRequest")
    @ResponsePayload
    public GetPokemonAbilitiesResponse getPokemonAbilities(@RequestPayload GetPokemonAbilitiesRequest request) {
        var clientIp = requestServlet.getRemoteAddr();
        PokemonResponse response = pokemonService.getPokemonByName(request.getName(), clientIp).block();
        GetPokemonAbilitiesResponse soapResponse = new GetPokemonAbilitiesResponse();
        Abilities abilities = new Abilities();
        abilities.getAbility().addAll(
            response.getAbilities().stream()
                .map(ability -> ability.getAbility().getName())
                .toList()
        );
        soapResponse.setAbilities(abilities);
        return soapResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonBaseExperienceRequest")
    @ResponsePayload
    public GetPokemonBaseExperienceResponse getPokemonBaseExperience(
            @RequestPayload GetPokemonBaseExperienceRequest request) {
        var clientIp = requestServlet.getRemoteAddr();
        PokemonResponse response = pokemonService.getPokemonByName(request.getName(), clientIp).block();
        GetPokemonBaseExperienceResponse soapResponse = new GetPokemonBaseExperienceResponse();
        soapResponse.setBaseExperience(response.getBaseExperience());
        return soapResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonHeldItemsRequest")
    @ResponsePayload
    public GetPokemonHeldItemsResponse getPokemonHeldItems(@RequestPayload GetPokemonHeldItemsRequest request) {
        var clientIp = requestServlet.getRemoteAddr();
        PokemonResponse response = pokemonService.getPokemonByName(request.getName(), clientIp).block();
        GetPokemonHeldItemsResponse soapResponse = new GetPokemonHeldItemsResponse();
        HeldItems heldItems = new HeldItems();
        heldItems.getItem().addAll(
            response.getHeldItems().stream()
                .map(item -> item.getItem().getName())
                .toList()
        );
        soapResponse.setHeldItems(heldItems);
        return soapResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonIdRequest")
    @ResponsePayload
    public GetPokemonIdResponse getPokemonId(@RequestPayload GetPokemonIdRequest request) {
        var clientIp = requestServlet.getRemoteAddr();
        PokemonResponse response = pokemonService.getPokemonByName(request.getName(), clientIp).block();
        GetPokemonIdResponse soapResponse = new GetPokemonIdResponse();
        soapResponse.setId(response.getId());
        return soapResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonNameRequest")
    @ResponsePayload
    public GetPokemonNameResponse getPokemonName(@RequestPayload GetPokemonNameRequest request) {
        var clientIp = requestServlet.getRemoteAddr();
        PokemonResponse response = pokemonService.getPokemonByName(request.getName(), clientIp).block();
        GetPokemonNameResponse soapResponse = new GetPokemonNameResponse();
        soapResponse.setName(response.getName());
        return soapResponse;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getPokemonLocationRequest")
    @ResponsePayload
    public GetPokemonLocationResponse getPokemonLocation(@RequestPayload GetPokemonLocationRequest request) {
        var clientIp = requestServlet.getRemoteAddr();
        PokemonResponse response = pokemonService.getPokemonByName(request.getName(), clientIp).block();
        GetPokemonLocationResponse soapResponse = new GetPokemonLocationResponse();
        soapResponse.setLocationAreaEncounters(response.getLocationAreaEncounters());
        return soapResponse;
    }
}
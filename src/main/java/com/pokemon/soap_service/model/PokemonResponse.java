package com.pokemon.soap_service.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PokemonResponse {
    private Integer id;
    private String name;
    private List<Ability> abilities;

    @JsonProperty("base_experience")
    private Integer baseExperience;

    @JsonProperty("held_items")
    private List<HeldItem> heldItems;

    @JsonProperty("location_area_encounters")
    private String locationAreaEncounters;

    @Data
    public static class Ability {
        private AbilityDetail ability;
        @JsonProperty("is_hidden")
        private boolean isHidden;
        private Integer slot;
    }

    @Data
    public static class AbilityDetail {
        private String name;
        private String url;
    }

    @Data
    public static class HeldItem {
        private ItemDetail item;
    }

    @Data
    public static class ItemDetail {
        private String name;
        private String url;
    }
}

package com.pokemon.soap_service.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Pokemon SOAP Service API")
                        .version("1.0")
                        .description(
                                "Este servicio SOAP consume la PokeAPI REST y proporciona información detallada sobre Pokémon.")
                        .contact(new Contact()
                                .name("Mario Cortes")
                                .email("mariocortes.sand@gmail.com")))
                .servers(List.of(new Server()
                        .url("http://localhost:8080")
                        .description("Local Environment")));
    }
}

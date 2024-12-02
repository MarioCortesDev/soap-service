package com.pokemon.soap_service.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.MediaType;
@Tag(name = "Pokemon SOAP Service", description = "Documentación del servicio SOAP de Pokemon")
@RestController
@RequestMapping("/api/docs")
public class ApiDocumentationController {

    @Operation(
        summary = "Obtener WSDL",
        description = "Obtener el WSDL del servicio SOAP de Pokemon",
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "WSDL del servicio",
                content = @Content(
                    mediaType = "text/xml",
                    examples = @ExampleObject(
                        name = "WSDL Response",
                        value = """
                        <?xml version="1.0" encoding="UTF-8"?>
                        <wsdl:definitions xmlns:wsdl="http://schemas.xmlsoap.org/wsdl/"
                                      xmlns:soap="http://pokemon.com/soap"
                                      xmlns:xs="http://www.w3.org/2001/XMLSchema"
                                      targetNamespace="http://pokemon.com/soap">
                            <!-- El WSDL completo está disponible en /ws/pokemon.wsdl -->
                        </wsdl:definitions>
                        """
                    )
                )
            )
        }
    )
    @GetMapping(value = "/wsdl", produces = MediaType.TEXT_XML_VALUE)
    public String getWsdl() {
        return "El WSDL está disponible en: http://localhost:8080/ws/pokemon.wsdl";
    }

    @Operation(
        summary = "Obtener habilidades de un Pokémon",
        description = "Endpoint SOAP para obtener las habilidades de un Pokémon por su nombre",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "text/xml",
                examples = @ExampleObject(
                    name = "Request Abilities",
                    value = """
                    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://pokemon.com/soap">
                       <soapenv:Header/>
                       <soapenv:Body>
                          <soap:getPokemonAbilitiesRequest>
                             <soap:name>pikachu</soap:name>
                          </soap:getPokemonAbilitiesRequest>
                       </soapenv:Body>
                    </soapenv:Envelope>
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Habilidades del Pokémon",
                content = @Content(
                    mediaType = "text/xml",
                    examples = @ExampleObject(
                        value = """
                        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
                           <SOAP-ENV:Body>
                              <ns2:getPokemonAbilitiesResponse xmlns:ns2="http://pokemon.com/soap">
                                 <ns2:abilities>
                                    <ns2:ability>static</ns2:ability>
                                    <ns2:ability>lightning-rod</ns2:ability>
                                 </ns2:abilities>
                              </ns2:getPokemonAbilitiesResponse>
                           </SOAP-ENV:Body>
                        </SOAP-ENV:Envelope>
                        """
                    )
                )
            )
        }
    )
    @PostMapping(value = "/pokemon/abilities", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public String documentAbilitiesEndpoint() {
        return "Este es un endpoint de documentación. Para usar el servicio SOAP real, utilice /ws";
    }

    @Operation(
        summary = "Obtener experiencia base de un Pokémon",
        description = "Endpoint SOAP para obtener la experiencia base de un Pokémon por su nombre",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "text/xml",
                examples = @ExampleObject(
                    name = "Request Base Experience",
                    value = """
                    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://pokemon.com/soap">
                       <soapenv:Header/>
                       <soapenv:Body>
                          <soap:getPokemonBaseExperienceRequest>
                             <soap:name>pikachu</soap:name>
                          </soap:getPokemonBaseExperienceRequest>
                       </soapenv:Body>
                    </soapenv:Envelope>
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Experiencia base del Pokémon",
                content = @Content(
                    mediaType = "text/xml",
                    examples = @ExampleObject(
                        value = """
                        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
                           <SOAP-ENV:Body>
                              <ns2:getPokemonBaseExperienceResponse xmlns:ns2="http://pokemon.com/soap">
                                 <ns2:baseExperience>112</ns2:baseExperience>
                              </ns2:getPokemonBaseExperienceResponse>
                           </SOAP-ENV:Body>
                        </SOAP-ENV:Envelope>
                        """
                    )
                )
            )
        }
    )
    @PostMapping(value = "/pokemon/base-experience", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public String documentBaseExperienceEndpoint() {
        return "Este es un endpoint de documentación. Para usar el servicio SOAP real, utilice /ws";
    }

    @Operation(
        summary = "Obtener objetos equipados de un Pokémon",
        description = "Endpoint SOAP para obtener los objetos equipados de un Pokémon por su nombre",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "text/xml",
                examples = @ExampleObject(
                    name = "Request Held Items",
                    value = """
                    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://pokemon.com/soap">
                       <soapenv:Header/>
                       <soapenv:Body>
                          <soap:getPokemonHeldItemsRequest>
                             <soap:name>pikachu</soap:name>
                          </soap:getPokemonHeldItemsRequest>
                       </soapenv:Body>
                    </soapenv:Envelope>
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Objetos equipados del Pokémon",
                content = @Content(
                    mediaType = "text/xml",
                    examples = @ExampleObject(
                        value = """
                        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
                           <SOAP-ENV:Body>
                              <ns2:getPokemonHeldItemsResponse xmlns:ns2="http://pokemon.com/soap">
                                 <ns2:heldItems>
                                    <ns2:item>light-ball</ns2:item>
                                 </ns2:heldItems>
                              </ns2:getPokemonHeldItemsResponse>
                           </SOAP-ENV:Body>
                        </SOAP-ENV:Envelope>
                        """
                    )
                )
            )
        }
    )
    @PostMapping(value = "/pokemon/held-items", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public String documentHeldItemsEndpoint() {
        return "Este es un endpoint de documentación. Para usar el servicio SOAP real, utilice /ws";
    }

    @Operation(
        summary = "Obtener ID de un Pokémon",
        description = "Endpoint SOAP para obtener el ID de un Pokémon por su nombre",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "text/xml",
                examples = @ExampleObject(
                    name = "Request ID",
                    value = """
                    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://pokemon.com/soap">
                       <soapenv:Header/>
                       <soapenv:Body>
                          <soap:getPokemonIdRequest>
                             <soap:name>pikachu</soap:name>
                          </soap:getPokemonIdRequest>
                       </soapenv:Body>
                    </soapenv:Envelope>
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "ID del Pokémon",
                content = @Content(
                    mediaType = "text/xml",
                    examples = @ExampleObject(
                        value = """
                        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
                           <SOAP-ENV:Body>
                              <ns2:getPokemonIdResponse xmlns:ns2="http://pokemon.com/soap">
                                 <ns2:id>25</ns2:id>
                              </ns2:getPokemonIdResponse>
                           </SOAP-ENV:Body>
                        </SOAP-ENV:Envelope>
                        """
                    )
                )
            )
        }
    )
    @PostMapping(value = "/pokemon/id", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public String documentIdEndpoint() {
        return "Este es un endpoint de documentación. Para usar el servicio SOAP real, utilice /ws";
    }

    @Operation(
        summary = "Obtener nombre de un Pokémon",
        description = "Endpoint SOAP para obtener el nombre normalizado de un Pokémon",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "text/xml",
                examples = @ExampleObject(
                    name = "Request Name",
                    value = """
                    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://pokemon.com/soap">
                       <soapenv:Header/>
                       <soapenv:Body>
                          <soap:getPokemonNameRequest>
                             <soap:name>pikachu</soap:name>
                          </soap:getPokemonNameRequest>
                       </soapenv:Body>
                    </soapenv:Envelope>
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "Nombre normalizado del Pokémon",
                content = @Content(
                    mediaType = "text/xml",
                    examples = @ExampleObject(
                        value = """
                        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
                           <SOAP-ENV:Body>
                              <ns2:getPokemonNameResponse xmlns:ns2="http://pokemon.com/soap">
                                 <ns2:name>pikachu</ns2:name>
                              </ns2:getPokemonNameResponse>
                           </SOAP-ENV:Body>
                        </SOAP-ENV:Envelope>
                        """
                    )
                )
            )
        }
    )
    @PostMapping(value = "/pokemon/name", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public String documentNameEndpoint() {
        return "Este es un endpoint de documentación. Para usar el servicio SOAP real, utilice /ws";
    }

    @Operation(
        summary = "Obtener ubicaciones de encuentro de un Pokémon",
        description = "Endpoint SOAP para obtener las ubicaciones donde se puede encontrar un Pokémon",
        requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
            required = true,
            content = @Content(
                mediaType = "text/xml",
                examples = @ExampleObject(
                    name = "Request Location",
                    value = """
                    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://pokemon.com/soap">
                       <soapenv:Header/>
                       <soapenv:Body>
                          <soap:getPokemonLocationRequest>
                             <soap:name>pikachu</soap:name>
                          </soap:getPokemonLocationRequest>
                       </soapenv:Body>
                    </soapenv:Envelope>
                    """
                )
            )
        ),
        responses = {
            @ApiResponse(
                responseCode = "200",
                description = "URL de ubicaciones del Pokémon",
                content = @Content(
                    mediaType = "text/xml",
                    examples = @ExampleObject(
                        value = """
                        <SOAP-ENV:Envelope xmlns:SOAP-ENV="http://schemas.xmlsoap.org/soap/envelope/">
                           <SOAP-ENV:Body>
                              <ns2:getPokemonLocationResponse xmlns:ns2="http://pokemon.com/soap">
                                 <ns2:locationAreaEncounters>https://pokeapi.co/api/v2/pokemon/25/encounters</ns2:locationAreaEncounters>
                              </ns2:getPokemonLocationResponse>
                           </SOAP-ENV:Body>
                        </SOAP-ENV:Envelope>
                        """
                    )
                )
            )
        }
    )
    @PostMapping(value = "/pokemon/location", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
    public String documentLocationEndpoint() {
        return "Este es un endpoint de documentación. Para usar el servicio SOAP real, utilice /ws";
    }
}
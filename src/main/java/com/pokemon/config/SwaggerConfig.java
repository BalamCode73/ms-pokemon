package com.pokemon.config;


import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h1>@Configuration()</h1>
 * Clase de configuracion para swagger
 * @return  Nada
 * @author  Manuel Dirsio
 * @version 1.0
 * @since   31/05/2024
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI springOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Microservicio")
                        .description("Microservicio para obtener pokemons")
                        .version("v0.0.1")
                        .license(new License().name("PokeService").url("https://www.dirsio.com./")))
                .externalDocs(new ExternalDocumentation()
                        .description("v1")
                        .url("https://www.dirsio.com/"));
    }
}
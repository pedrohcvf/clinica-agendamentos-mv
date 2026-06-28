package com.pedro.clinica.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Clínica Agendamentos MV")
                        .description("API REST para gerenciamento de agendamentos de consultas — Teste Técnico MV")
                        .version("1.0.0"));
    }
}
package com.diazero.incident.configurer;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfiguration {
    @Bean
    public OpenAPI incidentConfigurationDoc() {
        return new OpenAPI()
                .info(new Info().title("Incident Diazero api")
                        .description("incident management api")
                        .version("v0.0.1"))
                .externalDocs(new ExternalDocumentation()
                        .description("incident wiki configuration and start-up instructions")
                        .url("https://github.com/lucasciannella/incidentApi"));
    }
}
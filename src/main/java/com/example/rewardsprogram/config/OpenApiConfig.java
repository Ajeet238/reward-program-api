package com.example.rewardsprogram.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for OpenAPI / Swagger documentation.
 */
@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI rewardsProgramOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rewards Program API")
                        .description("API for calculating monthly and total reward points per customer")
                        .version("1.0.0")
                        .contact(new Contact().name("Rewards API Support").email("support@example.com"))
                        .license(new License().name("Apache 2.0").url("http://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project README")
                        .url("https://github.com/Ajeet238/reward-program-api"));
    }
}

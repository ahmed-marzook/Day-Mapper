package com.kaizenflow.daymapper.configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("Day Mapper API")
                .version("V1")
                .description("API documentation for Day Mapper Todo Application"))
        .components(
            new Components()
                .addSecuritySchemes(
                    "basicAuth",
                    new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("basic")
                        .description(
                            """
                                Basic Authentication:
                                - Email: Your registered email address
                                - Password: Your account password

                                Example:
                                Email: user@example.com
                                Password: yourpassword
                                """)));
  }
}

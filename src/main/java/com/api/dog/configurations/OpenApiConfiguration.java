package com.api.dog.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("DOGS CRUD API")
                        .version("1.0.0")
                        .description("This API allows you to read all dogs or a specific one, create, edit and delete a dog."));
    }
}

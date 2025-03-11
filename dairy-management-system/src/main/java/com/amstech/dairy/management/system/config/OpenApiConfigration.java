package com.amstech.dairy.management.system.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfigration {

    @Bean
    public OpenAPI defineOpenApi() {
        Contact myContact = new Contact().name("Pritam");

        Info information = new Info()
                .title("Dairy Management System")
                .version("1.0")
                .description("This is a dairy management project")
                .contact(myContact);

        return new OpenAPI().info(information);
    }
}

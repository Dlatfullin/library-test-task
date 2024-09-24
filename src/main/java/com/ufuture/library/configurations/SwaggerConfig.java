package com.ufuture.library.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(info = @Info(
        title = "Library API",
        description = "U-Future test task", version = "1.0.0",
        contact = @Contact(
                name = "Damir Latfullin",
                email = "itproofan@gmail.com",
                url = "https://www.linkedin.com/in/damir-latfullin-37b017299/"
        )
))
public class SwaggerConfig {

}

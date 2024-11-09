package tn.esprit.spring.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Dhaker Rhimi",
                        email = "dhaker.rhimi@esprit.tn"
                ),
                title = "Gestion User",
                version = "1.0.0",
                description = "SWAGGER : Gestion User"
        )
)
public class OpenApiConfig {
}

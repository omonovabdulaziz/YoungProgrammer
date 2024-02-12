package omo.nov.keyboardtrainer.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.utils.SpringDocUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Abdulaziz",
                        email = "omonov2006omonov@gmail.com",
                        url = "#!"
                ),
                description = "Yoshdasturchi klaviaturada tez yozishni mashq qilishni o'rgatuvchi loyiha",
                title = "YoshDasturchi",
                version = "1.0",
                license = @License(
                        name = "No License",
                        url = "No License"
                ),
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "#!"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "Bearer Auth"
                )
        }

)
@SecurityScheme(
        name = "Bearer Auth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {
}
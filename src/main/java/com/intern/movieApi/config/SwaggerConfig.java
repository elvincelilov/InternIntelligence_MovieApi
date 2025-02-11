package com.intern.movieApi.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springdoc.core.models.GroupedOpenApi;

@Configuration
public class SwaggerConfig {

    @Bean
    public GroupedOpenApi moviesApi() {
        return GroupedOpenApi.builder()
                .group("movie-api")
                .pathsToMatch("/api/**") // API endpoint-lərini göstərmək
                .addOpenApiCustomizer(openApi -> openApi.info(
                        new Info().title("Movie API")
                                .description("CRUD operations for movie database")
                                .version("1.0")
                                .contact(new Contact().name("Your Name").email("your-email@example.com"))
                ))
                .build();
    }
}

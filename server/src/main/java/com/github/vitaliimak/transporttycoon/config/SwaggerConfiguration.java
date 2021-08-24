package com.github.vitaliimak.transporttycoon.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import static java.util.Collections.singletonList;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.regex("/api.*"))
            .build()
            .securitySchemes(singletonList(securityScheme()))
            .securityContexts(singletonList(securityContext()));
    }

    private SecurityScheme securityScheme() {
        return HttpAuthenticationScheme.JWT_BEARER_BUILDER.name(HttpHeaders.AUTHORIZATION).build();
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(securityReferences())
            .build();
    }

    private List<SecurityReference> securityReferences() {
        return singletonList(
            new SecurityReference(HttpHeaders.AUTHORIZATION, new AuthorizationScope[]{new AuthorizationScope("global", "global")}));
    }
}

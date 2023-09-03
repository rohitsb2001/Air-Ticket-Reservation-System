package com.crimsonlogic.airticketreservationsystem.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

 

import java.util.Collections;
@Configuration
public class SwaggerConfiguration {

 

    private ApiInfo apiInfo() {
        return new ApiInfo("Blog REST APIs",
                "REST APIs for Blog Application",
                "1.0",
                "Terms of service",
                new Contact("CrimsonLogic", "www.crimsonlogic.com", "rohit@crimsonlogic.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

 

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }
}
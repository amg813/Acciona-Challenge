package com.twitter.accionaChallenge.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class AccionaConfiguration {

    private final static String DESCRIPTION = "Microservice that consumes tweets and storage them into a database";

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.twitter.accionaChallenge"))
                .paths(PathSelectors.any())
                .build()
                .forCodeGeneration(true)
                .apiInfo(new ApiInfoBuilder().title("Data Flow Microservice").description(DESCRIPTION).version("v1").build());
    }
}

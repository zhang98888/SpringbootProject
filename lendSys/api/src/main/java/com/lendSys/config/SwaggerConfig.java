package com.lendSys.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    // Swagger could help us to make API document

    // Docket used to package the API document
    @Bean
    public Docket getDocket(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2);// the style of the doc
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("API Document").version("2.0.1")
                .contact(new Contact("Wenting Zhang","","2552595z@student.gla.ac.uk"));

        ApiInfo apiInfo = apiInfoBuilder.build();
        docket.apiInfo(apiInfo).select()
                .apis(RequestHandlerSelectors.basePackage("com.lendSys.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
}

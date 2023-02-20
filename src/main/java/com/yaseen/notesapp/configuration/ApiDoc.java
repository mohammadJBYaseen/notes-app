package com.yaseen.notesapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class ApiDoc {

//    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("notes api")
//                .description("notes api")
//                .license("notes api")
//                .licenseUrl("https://notes.com/licenses/MIT")
//                .build();
//    }
//
//    @Bean
//    public Docket api() {
//        return new Docket(DocumentationType.OAS_30)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build();
//    }
}

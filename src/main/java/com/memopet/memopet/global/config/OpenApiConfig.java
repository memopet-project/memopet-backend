package com.memopet.memopet.global.config;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.swagger-ui.version}") String springdocVersion) {
        Info info = new Info()
                .title("메모펫 API 명세서")
                .version(springdocVersion)
                .description("메모펫 서비스를 위한 API 명세서");

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}

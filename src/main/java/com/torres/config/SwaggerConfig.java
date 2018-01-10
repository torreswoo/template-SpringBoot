package com.torres.config;

import org.springframework.beans.factory.annotation.Value;
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

    @Value("${info.app.name}")
    private String _app_title;
    @Value("${info.app.description}")
    private String _app_description;
    @Value("${info.app.version}")
    private String _app_version;

    @Value("${info.developer.name}")
    private String _developer_name;
    @Value("${info.developer.email}")
    private String _developer_email;
    @Value("${info.developer.url}")
    private String _developer_url;

    private String _basePackage = "com.torres";


    @Bean
    public Docket experimentVariationProvisionApi() {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName(_app_title)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage(_basePackage))
//          .paths(PathSelectors.regex("/abt/*"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title(_app_title)
            .description(_app_description)
            .contact(new Contact(_developer_name, _developer_url, _developer_email))
            .version(_app_version)
            .build();
    }
}

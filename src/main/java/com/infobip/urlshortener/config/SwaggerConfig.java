package com.infobip.urlshortener.config;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

  /**
   * api documentation
   */
  @Bean
  public Docket apiDocs() {
    Set<String> protocols = new HashSet<>();
    protocols.add("http");
    protocols.add("https");
    return new Docket(SWAGGER_2)
        .protocols(protocols)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.infobip.urlshortener"))
        .paths(PathSelectors.any())
        .build().apiInfo(apiInfo());
  }

  /**
   * api information
   */
  private ApiInfo apiInfo() {
    return new ApiInfo(
        "URL shortener assignment",
        "URL service for shortening long urls providing statistics based on user usage",
        "0.4.0",
        "TOS URL",
        new Contact("Peter Urban", "", ""),
        "LICENSE", "LICENSE URL", Collections.emptyList());
  }

}

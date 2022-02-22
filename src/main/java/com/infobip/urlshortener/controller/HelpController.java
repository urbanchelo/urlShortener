package com.infobip.urlshortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class HelpController {

  @ApiOperation(value = "Some basic information regarding usage of app", notes = "It has terrible styling")
  @GetMapping("/help")
  public String help() {
    return "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<body>\n" +
        "<h1>URL Shortener</h1>\n" +
        "<h2>Installation</h2>\n" +
        "\n" +
        "<ol>\n" +
        "    <li>Run 'mvn clean package' to create executable JAR file</li>\n" +
        "    <li>JAR file is contained in 'root/target/urlShortener-0.0.1-SNAPSHOT.jar'</li>\n" +
        "    <li>Find this file through any kind of cmd/terminal and run command 'java -jar urlShortener-0.0.1-SNAPSHOT.jar'</li>\n" +
        "    <li>This will start up whole application</li>\n" +
        "    <li>Add row (127.0.0.1 http://short.com/) you your host file</li>\n" +
        "</ol>\n" +
        "\n" +
        "<h2>Usage</h2>\n" +
        "\n" +
        "<ol>\n" +
        "    <li>Swagger documentation can be found at 'localhost:8080/swagger-ui/index.html'. Here you can find documentation to all endpoints</li>\n" +
        "    <li>For using app, user need account</li>\n" +
        "    <li>Once account is create, endpoints are using basic auth (name/password), but there are exceptions like 'swagger-ui/index.html' or '/help'</li>\n" +
        "    <li>For shortening of URL please use '/register' endpoint, which will return short version of URL</li>\n" +
        "    <li>Short URL has 2 parts: base (http://short.com/) and uuid (random 10 alphanumeric characters), in result (http://short.com/uikj215ll9)</li>\n" +
        "    <li>To be redirected to original URL, you need to add port after http://short.com, so it will look like this (http://short.com:port/uikj215ll9)</li>\n" +
        "    <li>Everytime short URL is called statistcs are incremented and are available at /statistic</li>\n" +
        "</ol>\n" +
        "\n" +
        "</body>\n" +
        "</html>\n" +
        "\n";
  }

}

package com.infobip.urlshortener.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
public class HelpController {

  @ApiOperation(value = "Some basic information regarding usage of app", notes = "It has terrible styling")
  @GetMapping("/help")
  public String help() {
    return "<!DOCTYPE HTML>\n" +
        "<html xmlns:th=\"http://www.thymeleaf.org\" lang=\"\">\n" +
        "<head>\n" +
        "    <title>Getting Started: Serving Web Content</title>\n" +
        "    <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />\n" +
        "</head>\n" +
        "<body>\n" +
        "<p th:text=\"'Hello, ' + ${name} + '!'\"></p>\n" +
        "</body>\n" +
        "</html>\n";
  }

}

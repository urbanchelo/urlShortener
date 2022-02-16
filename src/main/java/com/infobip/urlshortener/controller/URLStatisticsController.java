package com.infobip.urlshortener.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infobip.urlshortener.service.AccountService;
import com.infobip.urlshortener.service.URLStatisticsService;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@RestController
@RequestMapping("statistic")
public class URLStatisticsController {

  private final URLStatisticsService urlStatisticsService;
  private final AccountService accountService;

  public URLStatisticsController(final URLStatisticsService urlStatisticsService, final AccountService accountService) {
    this.urlStatisticsService = urlStatisticsService;
    this.accountService = accountService;
  }

  @GetMapping("/{accountId}")
  public ResponseEntity<Map<String, Integer>> getStatisticsForAccount(@RequestHeader(AUTHORIZATION) String authorizationHeader,
      @PathVariable("accountId") String accountId) {

    // todo proper authentication
    if (accountService.accountExists(accountId)) {
      return new ResponseEntity<>(urlStatisticsService.getStatisticsForAccount(accountId), HttpStatus.OK);
    }

    throw new RuntimeException("User not found!");
  }
}

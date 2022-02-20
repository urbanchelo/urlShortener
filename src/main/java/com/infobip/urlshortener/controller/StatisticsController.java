package com.infobip.urlshortener.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infobip.urlshortener.service.AccountService;
import com.infobip.urlshortener.service.StatisticsService;
import io.swagger.annotations.ApiOperation;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("statistic")
public class StatisticsController {

  private final StatisticsService statisticsService;
  private final AccountService accountService;

  public StatisticsController(final StatisticsService statisticsService, final AccountService accountService) {
    this.statisticsService = statisticsService;
    this.accountService = accountService;
  }

  @ApiOperation(value = "Get user statistics containing number of calls per URL")
  @GetMapping("/{accountId}")
  public ResponseEntity<Map<String, Integer>> getStatisticsForAccount(@RequestHeader(AUTHORIZATION) String authorizationHeader,
      @PathVariable("accountId") String accountId) {

    // todo proper authentication
    if (accountService.accountExists(authorizationHeader)) {
      return new ResponseEntity<>(statisticsService.getStatisticsForAccount(accountId), OK);
    }

    throw new RuntimeException("User not found!");
  }
}

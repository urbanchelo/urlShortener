package com.infobip.urlshortener.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infobip.urlshortener.service.AccountService;
import com.infobip.urlshortener.service.StatisticsService;
import io.swagger.annotations.ApiOperation;
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
  @GetMapping()
  public ResponseEntity<Map<String, Integer>> getStatisticsForAccount() {
    return new ResponseEntity<>(statisticsService.getStatistics(), OK);
  }
}

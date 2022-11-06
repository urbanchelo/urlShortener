package com.infobip.urlshortener.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infobip.urlshortener.service.StatisticsService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.ResponseEntity.ok;

@RequiredArgsConstructor
@RestController
@RequestMapping("statistic")
public class StatisticsController {

  private final StatisticsService statisticsService;

  @ApiOperation(value = "Get user statistics containing number of calls per URL")
  @GetMapping()
  public ResponseEntity<Map<String, Integer>> getStatisticsForAccount() {
    return ok(statisticsService.getStatistics());
  }
}

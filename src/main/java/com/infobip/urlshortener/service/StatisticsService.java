package com.infobip.urlshortener.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.infobip.urlshortener.domain.Statistics;
import com.infobip.urlshortener.repository.StatisticsRepository;
import static java.util.stream.Collectors.toMap;

@Service
public class StatisticsService {

  private final StatisticsRepository statisticsRepository;

  public StatisticsService(final StatisticsRepository statisticsRepository) {
    this.statisticsRepository = statisticsRepository;
  }

  public Map<String, Integer> getStatisticsForAccount(String accountId) {
    var statistics = statisticsRepository.findByAccountId(accountId);

    if (!statistics.isEmpty()) {
      return statistics.stream().collect(
          toMap(stat -> stat.getUrlStatisticsId().getUrlId().getOriginalUrl(), Statistics::getCallCount, (a, b) -> b, HashMap::new));
    }

    return new HashMap<>();
  }

}

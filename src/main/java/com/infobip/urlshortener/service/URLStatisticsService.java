package com.infobip.urlshortener.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.infobip.urlshortener.domain.URLStatistics;
import com.infobip.urlshortener.repository.URLStatisticsRepository;
import static java.util.stream.Collectors.toMap;

@Service
public class URLStatisticsService {

  private final URLStatisticsRepository urlStatisticsRepository;

  public URLStatisticsService(final URLStatisticsRepository urlStatisticsRepository) {
    this.urlStatisticsRepository = urlStatisticsRepository;
  }

  public Map<String, Integer> getStatisticsForAccount(String accountId) {
    var statistics = urlStatisticsRepository.findByAccountId(accountId);

    if (!statistics.isEmpty()) {
      return statistics.stream().collect(
          toMap(stat -> stat.getUrlStatisticsId().getUrlId().getOriginalUrl(), URLStatistics::getCallCount, (a, b) -> b, HashMap::new));
    }

    return new HashMap<>();
  }

}

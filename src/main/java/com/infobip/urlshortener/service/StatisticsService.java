package com.infobip.urlshortener.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.infobip.urlshortener.repository.StatisticsRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class StatisticsService {

  private final StatisticsRepository statisticsRepository;

  public Map<String, Integer> getStatistics() {
    var statistics = statisticsRepository.findAll();
    var statisticsMap = new HashMap<String, Integer>();
    var statisticsIterator = statistics.iterator();

    while (statisticsIterator.hasNext()) {
      var next = statisticsIterator.next();
      statisticsMap.put(next.getUrlLinks().getOriginalUrl(), next.getCallCount());
    }

    return statisticsMap;
  }

}

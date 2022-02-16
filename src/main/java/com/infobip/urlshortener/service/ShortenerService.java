package com.infobip.urlshortener.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infobip.urlshortener.domain.Statistics;
import com.infobip.urlshortener.domain.StatisticsId;
import com.infobip.urlshortener.domain.URLinks;
import com.infobip.urlshortener.dto.url.URLRequestDto;
import com.infobip.urlshortener.dto.url.URLResponseDto;
import com.infobip.urlshortener.repository.ShortenerRepository;
import com.infobip.urlshortener.repository.StatisticsRepository;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Service
@Transactional
public class ShortenerService {

  public static final String HTTP_SHORT_URL = "http://short.com/";
  private final ShortenerRepository shortenerRepository;
  private final StatisticsRepository statisticsRepository;

  public ShortenerService(final ShortenerRepository shortenerRepository,
      final StatisticsRepository statisticsRepository) {
    this.shortenerRepository = shortenerRepository;
    this.statisticsRepository = statisticsRepository;
  }

  public URLResponseDto shortUrl(URLRequestDto dto) {
    var optionalUrlLinks = shortenerRepository.findByOriginalUrl(dto.getUrl());

    if (optionalUrlLinks.isEmpty()) {
      var urlLinks = new URLinks(dto.getUrl(), HTTP_SHORT_URL + randomAlphanumeric(10));
      var saved = shortenerRepository.save(urlLinks);
      return new URLResponseDto(saved.getShortUrl());
    }

    return null;
  }

  public String getOriginalUrl(String uuid, String accountId) {
    var optionalUrlLinks = shortenerRepository.findByShortUrl(HTTP_SHORT_URL + uuid);

    if (optionalUrlLinks.isPresent()) {
      var optionalStatistics = statisticsRepository.findByAccountIdAndUrlId(accountId, optionalUrlLinks.get().getId());

      Statistics statistics;
      if (optionalStatistics.isPresent()) {
        statistics = optionalStatistics.get();
        statistics.increaseCallsCount();
      } else {
        statistics = new Statistics();
        statistics.setUrlStatisticsId(new StatisticsId(accountId, optionalUrlLinks.get()));
        statistics.setCallCount(1);
      }
      statisticsRepository.save(statistics);

      return optionalUrlLinks.get().getOriginalUrl();
    }

    return null;
  }

}

package com.infobip.urlshortener.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infobip.urlshortener.domain.URLinks;
import com.infobip.urlshortener.domain.URLStatistics;
import com.infobip.urlshortener.domain.URLStatisticsId;
import com.infobip.urlshortener.dto.url.URLRequestDto;
import com.infobip.urlshortener.dto.url.URLResponseDto;
import com.infobip.urlshortener.repository.URLRepository;
import com.infobip.urlshortener.repository.URLStatisticsRepository;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Service
@Transactional
public class URLService {

  private final URLRepository urlRepository;
  private final URLStatisticsRepository urlStatisticsRepository;

  public URLService(final URLRepository urlRepository,
      final URLStatisticsRepository urlStatisticsRepository) {
    this.urlRepository = urlRepository;
    this.urlStatisticsRepository = urlStatisticsRepository;
  }

  public URLResponseDto shortUrl(URLRequestDto dto) {
    var optionalUrlLinks = urlRepository.findByOriginalUrl(dto.getUrl());

    if (optionalUrlLinks.isEmpty()) {
      var urlLinks = new URLinks(dto.getUrl(), "http://short.com/" + randomAlphanumeric(10));
      var saved = urlRepository.save(urlLinks);
      return new URLResponseDto(saved.getShortUrl());
    }

    return null;
  }

  public String getOriginalUrl(String uuid, String accountId) {
    var optionalUrlLinks = urlRepository.findByShortUrl("http://short.com/" + uuid);

    if (optionalUrlLinks.isPresent()) {
      var optionalStatistics = urlStatisticsRepository.findByAccountIdAndUrlId(accountId, optionalUrlLinks.get().getId());

      URLStatistics urlStatistics;
      if (optionalStatistics.isPresent()) {
        urlStatistics = optionalStatistics.get();
        urlStatistics.increaseCallsCount();
      } else {
        urlStatistics = new URLStatistics();
        urlStatistics.setUrlStatisticsId(new URLStatisticsId(accountId, optionalUrlLinks.get()));
        urlStatistics.setCallCount(1);
      }
      urlStatisticsRepository.save(urlStatistics);

      return optionalUrlLinks.get().getOriginalUrl();
    }

    return null;
  }

}

package com.infobip.urlshortener.service;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.infobip.urlshortener.domain.Statistics;
import com.infobip.urlshortener.domain.URLLinks;
import com.infobip.urlshortener.dto.url.URLRequestDto;
import com.infobip.urlshortener.exception.InvalidParamException;
import com.infobip.urlshortener.repository.ShortenerRepository;
import com.infobip.urlshortener.repository.StatisticsRepository;
import static com.infobip.urlshortener.constants.TestConstants.ORIGINAL_URL_1;
import static com.infobip.urlshortener.constants.TestConstants.SHORT_URL;
import static com.infobip.urlshortener.constants.TestConstants.URL_LINKS_ID;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ShortenerServiceTest {

  private URLRequestDto requestDto;
  private URLLinks urlLinks;
  private Statistics statistics;

  @Mock
  private StatisticsRepository statisticsRepository;

  @Mock
  private ShortenerRepository shortenerRepository;

  @InjectMocks
  private ShortenerService shortenerService;

  @Test
  public void testShortentUrl_whenNew_saveAndReturnShortLink() {
    when(shortenerRepository.findByOriginalUrl(ORIGINAL_URL_1)).thenReturn(Optional.empty());
    when(shortenerRepository.save(any())).thenReturn(urlLinks);

    var result = shortenerService.shortentUrl(requestDto);

    assertNotNull(result);
    assertEquals(SHORT_URL, result.getShortUrl());

    verify(shortenerRepository, times(1)).findByOriginalUrl(ORIGINAL_URL_1);
    verify(shortenerRepository, times(1)).save(any());
  }

  @Test
  public void testShortentUrl_whenAlreadyExists_returnShortLink() {
    when(shortenerRepository.findByOriginalUrl(ORIGINAL_URL_1)).thenReturn(Optional.of(urlLinks));

    var result = shortenerService.shortentUrl(requestDto);

    assertNotNull(result);
    assertEquals(SHORT_URL, result.getShortUrl());

    verify(shortenerRepository, times(1)).findByOriginalUrl(ORIGINAL_URL_1);
    verify(shortenerRepository, times(0)).save(any());
  }

  @Test
  public void testGetOriginalUrl_whenExists_returnOriginalLink() {
    when(shortenerRepository.findByShortUrl(SHORT_URL)).thenReturn(Optional.of(urlLinks));
    when(statisticsRepository.findByUrlId(URL_LINKS_ID)).thenReturn(Optional.of(statistics));

    var result = shortenerService.getOriginalUrl(URL_LINKS_ID);

    assertNotNull(result);
    assertEquals(ORIGINAL_URL_1, result);
    assertEquals(1, statistics.getCallCount());

    verify(shortenerRepository, times(1)).findByShortUrl(SHORT_URL);
    verify(statisticsRepository, times(1)).findByUrlId(URL_LINKS_ID);
    verify(statisticsRepository, times(1)).save(any());
  }

  @Test
  public void testGetOriginalUrl_whenNotExists_throwException() {
    when(shortenerRepository.findByShortUrl(SHORT_URL)).thenReturn(Optional.empty());

    var result = assertThrows(InvalidParamException.class, () -> shortenerService.getOriginalUrl(URL_LINKS_ID));

    assertEquals("Record for current URL doesn't exist.", result.getMessage());

    verify(shortenerRepository, times(1)).findByShortUrl(SHORT_URL);
    verifyNoInteractions(statisticsRepository);
  }

  @Before
  public void init() {
    requestDto = new URLRequestDto();
    requestDto.setUrl(ORIGINAL_URL_1);

    urlLinks = new URLLinks();
    urlLinks.setId(URL_LINKS_ID);
    urlLinks.setOriginalUrl(ORIGINAL_URL_1);
    urlLinks.setShortUrl(SHORT_URL);

    statistics = new Statistics();
  }

}

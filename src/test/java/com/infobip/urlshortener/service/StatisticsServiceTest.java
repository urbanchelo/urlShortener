package com.infobip.urlshortener.service;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.infobip.urlshortener.domain.Statistics;
import com.infobip.urlshortener.domain.URLLinks;
import com.infobip.urlshortener.repository.StatisticsRepository;
import static com.infobip.urlshortener.constants.TestConstants.ORIGINAL_URL_1;
import static com.infobip.urlshortener.constants.TestConstants.ORIGINAL_URL_2;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsServiceTest {

  private Statistics statistics1;
  private Statistics statistics2;

  @Mock
  private StatisticsRepository statisticsRepository;

  @InjectMocks
  private StatisticsService statisticsService;

  @Test
  public void testGetStatistics_whenExist_returnList() {
    when(statisticsRepository.findAll()).thenReturn(Arrays.asList(statistics1, statistics2));

    var result = statisticsService.getStatistics();

    assertNotNull(result);
    assertEquals(2, result.size());
    assertTrue(result.containsKey(ORIGINAL_URL_1));
    assertTrue(result.containsKey(ORIGINAL_URL_2));
  }

  @Test
  public void testGetStatistics_whenNotExist_returnEmptyList() {
    when(statisticsRepository.findAll()).thenReturn(new ArrayList<>());

    var result = statisticsService.getStatistics();

    assertNotNull(result);
    assertTrue(result.isEmpty());
  }

  @Before
  public void init() {
    URLLinks urlLinks1 = new URLLinks();
    urlLinks1.setOriginalUrl(ORIGINAL_URL_1);

    URLLinks urlLinks2 = new URLLinks();
    urlLinks2.setOriginalUrl(ORIGINAL_URL_2);

    statistics1 = new Statistics();
    statistics1.setCallCount(3);
    statistics1.setUrlLinks(urlLinks1);

    statistics2 = new Statistics();
    statistics2.setCallCount(9);
    statistics2.setUrlLinks(urlLinks2);
  }

}

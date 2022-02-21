package com.infobip.urlshortener.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.infobip.urlshortener.service.StatisticsService;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class StatisticsControllerTest {

  private MockMvc mockMvc;

  @Mock
  private StatisticsService statisticsService;

  @InjectMocks
  private StatisticsController statisticsController;

  @Test
  public void testGetStatisticsForAccount_shouldCallService_whenCalled() throws Exception {
    mockMvc.perform(get("/statistic").contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isOk()).andReturn();

    verify(statisticsService, times(1)).getStatistics();
  }


  @Before
  public void init() {
    mockMvc = standaloneSetup(statisticsController).setControllerAdvice(new ExceptionHandlerController()).build();
  }

}

package com.infobip.urlshortener.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.infobip.urlshortener.service.ShortenerService;
import com.infobip.urlshortener.validator.RequestParamValidator;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class ShortenerControllerTest {

  private MockMvc mockMvc;

  @Mock
  private RequestParamValidator requestParamValidator;

  @Mock
  private ShortenerService shortenerService;

  @InjectMocks
  private ShortenerController shortenerController;

  @Test
  public void testShortenURL_shouldCallService_whenCalled() throws Exception {
    String requestBody = "{\n" +
        "\t\"redirectType\":302\n" +
        "}";

    mockMvc.perform(post("/register").contentType(APPLICATION_JSON_VALUE).content(requestBody))
        .andExpect(status().isFound()).andReturn();

    verify(requestParamValidator, times(1)).checkURLRequestBody(any());
    verify(shortenerService, times(1)).shortentUrl(any());
  }

  @Test
  public void testRedirectToOriginalURL_shouldCallService_whenCalled() throws Exception {
    String redirectUrl = "infobip.com";
    String shortUrlUuid = "uuid1";
    when(shortenerService.getOriginalUrl(shortUrlUuid)).thenReturn(redirectUrl);

    mockMvc.perform(get("/" + shortUrlUuid).contentType(APPLICATION_JSON_VALUE))
        .andExpect(status().isFound()).andReturn();

    verify(shortenerService, times(1)).getOriginalUrl(shortUrlUuid);
  }

  @Before
  public void init() {
    mockMvc = standaloneSetup(shortenerController).setControllerAdvice(new ExceptionHandlerController()).build();
  }

}

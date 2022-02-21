package com.infobip.urlshortener.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.infobip.urlshortener.service.AccountService;
import com.infobip.urlshortener.validator.RequestParamValidator;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(MockitoJUnitRunner.class)
public class AccountControllerTest {

  private MockMvc mockMvc;

  @Mock
  private RequestParamValidator requestParamValidator;

  @Mock
  private AccountService accountService;

  @InjectMocks
  private AccountController accountController;

  @Test
  public void testCreateAccount_shouldCallService_whenCalled() throws Exception {
    mockMvc.perform(post("/account").contentType(APPLICATION_JSON_VALUE).content("{}"))
        .andExpect(status().isOk()).andReturn();

    verify(requestParamValidator, times(1)).checkAccountRequestBody(any());
    verify(accountService, times(1)).save(any());
  }

  @Before
  public void init() {
    mockMvc = standaloneSetup(accountController).setControllerAdvice(new ExceptionHandlerController()).build();
  }

}

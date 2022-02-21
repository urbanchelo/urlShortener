package com.infobip.urlshortener.validator;

import org.junit.Before;
import org.junit.Test;

import com.infobip.urlshortener.dto.account.AccountRequestDto;
import com.infobip.urlshortener.dto.url.URLRequestDto;
import com.infobip.urlshortener.exception.InvalidParamException;
import static com.infobip.urlshortener.constants.TestConstants.ACCOUNT_ID;
import static com.infobip.urlshortener.constants.TestConstants.EMPTY_STRING;
import static com.infobip.urlshortener.constants.TestConstants.ORIGINAL_URL_1;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.springframework.http.HttpStatus.CONTINUE;
import static org.springframework.http.HttpStatus.FOUND;

public class RequestParamValidatorTest {

  private AccountRequestDto accountRequestDto;
  private URLRequestDto urlRequestDto;
  private RequestParamValidator validator;

  @Test
  public void testCheckURLRequestBody_whenDtoIsValid_dontThrowException() {
    validator.checkURLRequestBody(urlRequestDto);
  }

  @Test
  public void testCheckURLRequestBody_whenUrlIsMissing_throwException() {
    urlRequestDto.setUrl(null);
    var result = assertThrows(InvalidParamException.class, () -> validator.checkURLRequestBody(urlRequestDto));

    assertEquals("Mandatory field URL is missing", result.getMessage());

    urlRequestDto.setUrl(EMPTY_STRING);
    result = assertThrows(InvalidParamException.class, () -> validator.checkURLRequestBody(urlRequestDto));

    assertEquals("Mandatory field URL is missing", result.getMessage());
  }

  @Test
  public void testCheckURLRequestBody_whenRedirectTypeIsMissing_set302AsDefault() {
    urlRequestDto.setRedirectType(null);

    validator.checkURLRequestBody(urlRequestDto);

    assertEquals(FOUND.value(), urlRequestDto.getRedirectType().intValue());
  }

  @Test
  public void testCheckURLRequestBody_whenRedirectTypeIsNot301Nor302_throwsException() {
    urlRequestDto.setRedirectType(CONTINUE.value());
    var result = assertThrows(InvalidParamException.class, () -> validator.checkURLRequestBody(urlRequestDto));

    assertEquals("Redirect type is invalid.", result.getMessage());
  }

  @Test
  public void testCheckAccountRequestBody_whenAccountIdIsMissing_throwException() {
    accountRequestDto.setAccountId(null);
    var result = assertThrows(InvalidParamException.class, () -> validator.checkAccountRequestBody(accountRequestDto));

    assertEquals("Mandatory field accountId is missing or empty", result.getMessage());

    accountRequestDto.setAccountId(EMPTY_STRING);
    result = assertThrows(InvalidParamException.class, () -> validator.checkAccountRequestBody(accountRequestDto));

    assertEquals("Mandatory field accountId is missing or empty", result.getMessage());
  }

  @Before
  public void init() {
    validator = new RequestParamValidator();
    accountRequestDto = new AccountRequestDto();
    urlRequestDto = new URLRequestDto();

    urlRequestDto.setUrl(ORIGINAL_URL_1);
    urlRequestDto.setRedirectType(FOUND.value());

    accountRequestDto.setAccountId(ACCOUNT_ID);
  }

}

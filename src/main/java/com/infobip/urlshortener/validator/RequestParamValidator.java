package com.infobip.urlshortener.validator;

import org.springframework.stereotype.Component;

import com.infobip.urlshortener.dto.account.AccountRequestDto;
import com.infobip.urlshortener.dto.url.URLRequestDto;
import com.infobip.urlshortener.exception.InvalidParamException;
import static java.util.Objects.isNull;
import static org.apache.logging.log4j.util.Strings.isEmpty;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;
import static org.springframework.http.HttpStatus.valueOf;

@Component
public class RequestParamValidator {

  public void checkURLRequestBody(URLRequestDto dto) {
    if (isEmpty(dto.getUrl())) {
      throw new InvalidParamException("Mandatory field URL is missing");
    }

    var redirectType = dto.getRedirectType();
    if (isNull(redirectType)) {
      dto.setRedirectType(FOUND.value());
      return;
    }

    if (!FOUND.equals(valueOf(redirectType)) && !MOVED_PERMANENTLY.equals(valueOf(redirectType))) {
      throw new InvalidParamException("Redirect type is invalid.");
    }
  }

  public void checkAccountRequestBody(AccountRequestDto dto) {
    if (isEmpty(dto.getAccountId())) {
      throw new InvalidParamException("Mandatory field accountId is missing or empty");
    }
  }
}

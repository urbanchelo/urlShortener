package com.infobip.urlshortener.validator;

import org.springframework.stereotype.Component;

import com.infobip.urlshortener.dto.account.AccountRequestDto;
import com.infobip.urlshortener.dto.url.URLRequestDto;
import com.infobip.urlshortener.exception.InvalidParamException;
import static org.apache.logging.log4j.util.Strings.isEmpty;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@Component
public class RequestParamValidator {

  public void checkURLRequestBody(URLRequestDto dto) {
    if (isEmpty(dto.getUrl())) {
      throw new InvalidParamException("Mandatory field URL is missing");
    }

    var redirectType = dto.getRedirectType();
    if (redirectType == null) {
      dto.setRedirectType(FOUND.value());
      return;
    }

    if (FOUND.value() != redirectType && MOVED_PERMANENTLY.value() != redirectType) {
      throw new InvalidParamException("Redirect type is invalid.");
    }
  }

  public void checkAccountRequestBody(AccountRequestDto dto) {
    if (isEmpty(dto.getAccountId())) {
      throw new InvalidParamException("Mandatory field accountId is missing or empty");
    }
  }

}

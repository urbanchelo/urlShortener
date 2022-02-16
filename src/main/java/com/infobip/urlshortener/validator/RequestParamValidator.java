package com.infobip.urlshortener.validator;

import java.security.InvalidParameterException;

import org.springframework.stereotype.Component;

import com.infobip.urlshortener.dto.account.AccountRequestDto;
import com.infobip.urlshortener.dto.url.URLRequestDto;
import static org.apache.logging.log4j.util.Strings.isEmpty;
import static org.springframework.http.HttpStatus.FOUND;
import static org.springframework.http.HttpStatus.MOVED_PERMANENTLY;

@Component
public class RequestParamValidator {

  public void checkURLRequestBody(URLRequestDto dto) {
    if (isEmpty(dto.getUrl())) {
      throw new InvalidParameterException("Mandatory field URL is missing");
    }

    var redirectType = dto.getRedirectType();
    if (redirectType != null) {
      if (FOUND.value() != redirectType && MOVED_PERMANENTLY.value() != redirectType) {
        throw new InvalidParameterException("Redirect type is invalid.");
      }
    } else {
      dto.setRedirectType(FOUND.value());
    }
  }

  public void checkAccountRequestBody(AccountRequestDto dto) {
    if (isEmpty(dto.getAccountId())) {
      throw new InvalidParameterException("Mandatory field accountId is missing");
    }
  }

}

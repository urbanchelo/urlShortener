package com.infobip.urlshortener.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.infobip.urlshortener.dto.url.URLRequestDto;
import com.infobip.urlshortener.dto.url.URLResponseDto;
import com.infobip.urlshortener.service.AccountService;
import com.infobip.urlshortener.service.ShortenerService;
import com.infobip.urlshortener.validator.RequestParamValidator;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.valueOf;

@RestController
public class ShortenerController {

  private final ShortenerService shortenerService;
  private final AccountService accountService;
  private final RequestParamValidator paramValidator;

  public ShortenerController(final ShortenerService shortenerService, final AccountService accountService, final RequestParamValidator paramValidator) {
    this.shortenerService = shortenerService;
    this.accountService = accountService;
    this.paramValidator = paramValidator;
  }

  @PostMapping("/register")
  public ResponseEntity<URLResponseDto> shorUrl(@RequestBody URLRequestDto dto, @RequestHeader(AUTHORIZATION) String accountId) {
    // todo proper authentication
    if (accountService.accountExists(accountId)) {
      paramValidator.checkURLRequestBody(dto);
      return new ResponseEntity<>(shortenerService.shortUrl(dto), valueOf(dto.getRedirectType()));
    }

    throw new RuntimeException("User not found!");
  }

  @GetMapping("/{uuid}")
  public void shorUrl(@PathVariable String uuid, @RequestHeader(AUTHORIZATION) String accountId, HttpServletResponse response) throws IOException {
    // todo proper authentication
    if (accountService.accountExists(accountId)) {
      var found = shortenerService.getOriginalUrl(uuid, accountId);
      response.sendRedirect(found);
    }

    throw new RuntimeException("User not found!");
  }

}

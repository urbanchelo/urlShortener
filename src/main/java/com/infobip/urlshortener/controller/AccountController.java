package com.infobip.urlshortener.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.infobip.urlshortener.dto.account.AccountRequestDto;
import com.infobip.urlshortener.dto.account.AccountResponseDto;
import com.infobip.urlshortener.service.AccountService;
import com.infobip.urlshortener.validator.RequestParamValidator;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("account")
public class AccountController {

  private final AccountService accountService;
  private final RequestParamValidator paramValidator;

  public AccountController(final AccountService accountService, final RequestParamValidator paramValidator) {
    this.accountService = accountService;
    this.paramValidator = paramValidator;
  }

  /**
   * Endpoint for creating account.
   *
   * @param dto dto containing account id
   * @return dto with success flag, description and account password in case it's created
   */
  @PostMapping
  public ResponseEntity<AccountResponseDto> createAccount(@RequestBody AccountRequestDto dto) {
    paramValidator.checkAccountRequestBody(dto);
    return new ResponseEntity<>(accountService.save(dto), OK);
  }

}

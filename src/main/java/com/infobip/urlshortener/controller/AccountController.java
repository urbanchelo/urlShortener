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
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("account")
public class AccountController {

  private final AccountService accountService;
  private final RequestParamValidator paramValidator;

  /**
   * Endpoint for creating account.
   *
   * @param dto dto containing account id
   * @return dto with success flag, description and account password in case it's created
   */
  @Operation(summary = "Create new account when account doesn't exist", description = "Generates password when new account")
  @PostMapping
  public ResponseEntity<AccountResponseDto> createAccount(@RequestBody AccountRequestDto dto) {
    paramValidator.checkAccountRequestBody(dto);
    return ResponseEntity.ok(accountService.save(dto));
  }

}

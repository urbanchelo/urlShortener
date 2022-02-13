package com.infobip.urlshortener.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infobip.urlshortener.domain.Account;
import com.infobip.urlshortener.dto.AccountRequestDto;
import com.infobip.urlshortener.dto.AccountResponseDto;
import com.infobip.urlshortener.repository.AccountRepository;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;

@Service
@Transactional
public class AccountService {

  private final AccountRepository accountRepository;

  public AccountService(final AccountRepository accountRepository) {
    this.accountRepository = accountRepository;
  }

  /**
   * Saves user entity to DB. If account isn't present in DB, password is generated and success message is returned.
   * Otherwise, account isn't saved and failure message is returned
   *
   * @param dto dto containing account id
   * @return dto containing success flag, description and accoutn password
   */
  public AccountResponseDto save(AccountRequestDto dto) {
    var optionalAccount = accountRepository.findById(dto.getId());

    if (optionalAccount.isPresent()) {
      return new AccountResponseDto(false, "Account exists already!", null);
    }

    var saved = accountRepository.save(new Account(dto.getId(), randomAlphanumeric(8)));
    return new AccountResponseDto(true, "Account has been open!", saved.getPassword());

  }

}

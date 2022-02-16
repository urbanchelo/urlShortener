package com.infobip.urlshortener.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.infobip.urlshortener.domain.Account;
import com.infobip.urlshortener.dto.account.AccountRequestDto;
import com.infobip.urlshortener.dto.account.AccountResponseDto;
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
   * Saves user entity to DB. If account isn't present in DB, generated password and success message is returned.
   * Otherwise, account isn't saved and failure message is returned
   *
   * @param dto dto containing account id
   * @return dto containing success flag, description and account password
   */
  public AccountResponseDto save(AccountRequestDto dto) {
    if (accountExists(dto.getAccountId())) {
      return new AccountResponseDto(false, "Account exists already!", null);
    }

    var saved = accountRepository.save(new Account(dto.getAccountId(), randomAlphanumeric(8)));
    return new AccountResponseDto(true, "Account has been open!", saved.getPassword());
  }

  /**
   * Check whether account exists already in DB
   *
   * @param accountId account identifier
   * @return true if exists, otherwise false
   */
  public boolean accountExists(String accountId) {
    return accountRepository.findById(accountId).isPresent();
  }

}

package com.infobip.urlshortener.service;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.infobip.urlshortener.domain.Account;
import com.infobip.urlshortener.dto.account.AccountRequestDto;
import com.infobip.urlshortener.repository.AccountRepository;
import static com.infobip.urlshortener.constants.TestConstants.ACCOUNT_ID;
import static java.util.Optional.empty;
import static org.apache.commons.lang3.RandomStringUtils.randomAlphanumeric;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AccountServiceTest {

  private AccountRequestDto requestDto;
  private Account account;

  @Mock
  private AccountRepository accountRepository;

  @InjectMocks
  private AccountService accountService;

  @Test
  public void testSave_whenNewAccount_saveAndReturnSuccess() {
    when(accountRepository.findById(ACCOUNT_ID)).thenReturn(empty());
    when(accountRepository.save(any())).thenReturn(account);

    var result = accountService.save(requestDto);

    assertNotNull(result);
    assertTrue(result.isSuccess());
    assertEquals("Account has been open!", result.getDescription());
    assertNotNull(result.getPassword());
    assertEquals(8, result.getPassword().length());

    verify(accountRepository, times(1)).findById(ACCOUNT_ID);
    verify(accountRepository, times(1)).save(any());
  }

  @Test
  public void testSave_whenExistingAccount_dontSaveAndReturnFailure() {
    when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.of(account));

    var result = accountService.save(requestDto);

    assertNotNull(result);
    assertFalse(result.isSuccess());
    assertEquals("Account exists already!", result.getDescription());
    assertNull(result.getPassword());

    verify(accountRepository, times(1)).findById(ACCOUNT_ID);
    verify(accountRepository, times(0)).save(any());
  }

  @Test
  public void testAccountExists_whenExistingAccount_returnTrue() {
    when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.of(account));

    assertTrue(accountService.accountExists(ACCOUNT_ID));
  }

  @Test
  public void testAccountExists_whenNotExistingAccount_returnFalse() {
    when(accountRepository.findById(ACCOUNT_ID)).thenReturn(Optional.empty());

    assertFalse(accountService.accountExists(ACCOUNT_ID));
  }

  @Before
  public void init() {
    requestDto = new AccountRequestDto();
    requestDto.setAccountId(ACCOUNT_ID);

    account = new Account();
    account.setId(ACCOUNT_ID);
    account.setPassword(randomAlphanumeric(8));
  }

}

package com.infobip.urlshortener.security;

import javax.annotation.PostConstruct;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import com.infobip.urlshortener.repository.AccountRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final WebApplicationContext applicationContext;
  private AccountRepository accountRepository;

  public UserDetailsServiceImpl(final WebApplicationContext applicationContext) {
    this.applicationContext = applicationContext;
  }

  @PostConstruct
  public void completeSetup() {
    accountRepository = applicationContext.getBean(AccountRepository.class);
  }

  @Override
  public UserDetails loadUserByUsername(final String username) {
    var optionalAccount = accountRepository.findById(username);

    if (optionalAccount.isEmpty()) {
      throw new UsernameNotFoundException(username);
    }

    return new UserDetailsDto(optionalAccount.get());
  }

}

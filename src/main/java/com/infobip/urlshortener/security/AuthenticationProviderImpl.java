package com.infobip.urlshortener.security;

import java.util.ArrayList;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import com.infobip.urlshortener.exception.UnauthorizedException;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {

  private final UserDetailsServiceImpl userDetailsService;

  public AuthenticationProviderImpl(final UserDetailsServiceImpl userDetailsService) {
    this.userDetailsService = userDetailsService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    String name = authentication.getName();
    String password = authentication.getCredentials().toString();

    if (Strings.isEmpty(name)) {
      throw new UnauthorizedException("Account id is missing");
    }

    var userDetails = userDetailsService.loadUserByUsername(name);

    if (!userDetails.getUsername().equals(name) || !userDetails.getPassword().equals(password)) {
      throw new UnauthorizedException("Invalid credentials");
    }

    return new UsernamePasswordAuthenticationToken(name, password, new ArrayList<>());
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(UsernamePasswordAuthenticationToken.class);
  }
}

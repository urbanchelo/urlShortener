package com.infobip.urlshortener.security;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

import com.infobip.urlshortener.exception.UnauthorizedException;
import static com.infobip.urlshortener.constants.TestConstants.ACCOUNT_ID;
import static com.infobip.urlshortener.constants.TestConstants.ACCOUNT_PASSWORD;
import static com.infobip.urlshortener.constants.TestConstants.EMPTY_STRING;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class AuthenticationProviderImplTest {

  @Mock
  private UserDetails userDetails;

  @Mock
  private Authentication authentication;

  @Mock
  private UserDetailsServiceImpl userDetailsService;

  @InjectMocks
  private AuthenticationProviderImpl authenticationProvider;

  @Test
  public void testAuthenticate_whenValidCredentials_returnAuthenticationObject() {
    var result = authenticationProvider.authenticate(authentication);

    assertNotNull(result);
    assertEquals(ACCOUNT_ID, result.getName());
    assertEquals(ACCOUNT_PASSWORD, result.getCredentials().toString());
  }

  @Test
  public void testAuthenticate_whenNameIsMissing_throwUnauthorizedException() {
    when(authentication.getName()).thenReturn(null);

    var result = assertThrows(UnauthorizedException.class, () -> authenticationProvider.authenticate(authentication));

    assertEquals("Account id is missing", result.getMessage());
  }

  @Test
  public void testAuthenticate_whenPasswordIsMissing_throwUnauthorizedException() {
    when(authentication.getCredentials()).thenReturn(EMPTY_STRING);

    var result = assertThrows(UnauthorizedException.class, () -> authenticationProvider.authenticate(authentication));

    assertEquals("Invalid credentials", result.getMessage());
  }

  @Test
  public void testAuthenticate_whenPasswordIsWrong_throwUnauthorizedException() {
    when(authentication.getCredentials()).thenReturn(ACCOUNT_PASSWORD + "ib");

    var result = assertThrows(UnauthorizedException.class, () -> authenticationProvider.authenticate(authentication));

    assertEquals("Invalid credentials", result.getMessage());
  }

  @Before
  public void init() {
    when(authentication.getName()).thenReturn(ACCOUNT_ID);
    when(authentication.getCredentials()).thenReturn(ACCOUNT_PASSWORD);

    when(userDetails.getUsername()).thenReturn(ACCOUNT_ID);
    when(userDetails.getPassword()).thenReturn(ACCOUNT_PASSWORD);

    when(userDetailsService.loadUserByUsername(ACCOUNT_ID)).thenReturn(userDetails);
  }

}

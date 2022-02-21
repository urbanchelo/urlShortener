package com.infobip.urlshortener.config;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.context.WebApplicationContext;

import com.infobip.urlshortener.security.AuthenticationProviderImpl;
import com.infobip.urlshortener.security.UserDetailsServiceImpl;

@Configuration
@EnableWebSecurity
@ComponentScan({"com.infobip.urlshortener"})
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private WebApplicationContext applicationContext;

  @Autowired
  private AuthenticationProviderImpl authenticationProviderImpl;

  @Autowired
  private DataSource dataSource;

  private UserDetailsServiceImpl userDetailsService;

  @PostConstruct
  public void completeSetup() {
    userDetailsService = applicationContext.getBean(UserDetailsServiceImpl.class);
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService)
        .and()
        .authenticationProvider(authenticationProviderImpl)
        .jdbcAuthentication()
        .dataSource(dataSource);
  }

  @Override
  public void configure(WebSecurity web) {
    web.ignoring()
        .antMatchers("/resources/**")
        .antMatchers("/account")
        .antMatchers("/help")
        .antMatchers("/h2-console/**");
  }

  @Override
  protected void configure(final HttpSecurity http) throws Exception {
    http.csrf().disable()
        .authorizeRequests()
        .anyRequest().authenticated()
        .and()
        .httpBasic();
  }

}

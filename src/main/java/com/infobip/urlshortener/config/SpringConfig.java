package com.infobip.urlshortener.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;


@ComponentScan({"com.infobip.urlshortener"})
@Configuration
public class SpringConfig {

  @Autowired
  DataSource dataSource;

  @Bean
  public JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(dataSource);
  }

}

package com.infobip.urlshortener.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;


@ComponentScan({"com.infobip.urlshortener"})
@Configuration
@EnableWebMvc
public class SpringConfig {

  private final DataSource dataSource;

  public SpringConfig(final DataSource dataSource) {
    this.dataSource = dataSource;
  }

  @Bean
  public JdbcTemplate getJdbcTemplate() {
    return new JdbcTemplate(dataSource);
  }

}

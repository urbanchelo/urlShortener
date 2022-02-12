package com.infobip.urlshortener.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

@Configuration
public class DBConfig {

  @Bean
  public DataSource dataSource() {
    return new EmbeddedDatabaseBuilder()
        .setName("ib-dev")
        .setType(EmbeddedDatabaseType.H2)
        .addScript("db/create-db.sql")
        .addScript("db/insert-data.sql")
        .build();
  }

}

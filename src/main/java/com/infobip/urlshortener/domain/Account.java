package com.infobip.urlshortener.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Account {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "password")
  private String password;

  public Account() {
  }

  public Account(final String id, final String password) {
    this.id = id;
    this.password = password;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String email) {
    this.password = email;
  }
}

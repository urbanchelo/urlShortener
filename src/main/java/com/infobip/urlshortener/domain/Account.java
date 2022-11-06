package com.infobip.urlshortener.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Account {

  @Id
  @Column(name = "id")
  private String id;

  @Column(name = "password")
  private String password;

}

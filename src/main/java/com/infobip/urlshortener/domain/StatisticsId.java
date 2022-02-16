package com.infobip.urlshortener.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Embeddable
public class StatisticsId implements Serializable {

  @Column(name = "account_id")
  private String accountId;

  @ManyToOne
  @JoinColumn(name = "url_id", referencedColumnName = "id")
  private URLinks urlId;

  public StatisticsId() {
  }

  public StatisticsId(final String accountId, final URLinks urlId) {
    this.accountId = accountId;
    this.urlId = urlId;
  }

  public String getAccountId() {
    return accountId;
  }

  public void setAccountId(final String accountId) {
    this.accountId = accountId;
  }

  public URLinks getUrlId() {
    return urlId;
  }

  public void setUrlId(final URLinks urlId) {
    this.urlId = urlId;
  }
}

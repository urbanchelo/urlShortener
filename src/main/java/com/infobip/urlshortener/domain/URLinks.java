package com.infobip.urlshortener.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "url_links")
public class URLinks {

  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @Column(name = "original_url")
  private String originalUrl;

  @Column(name = "short_url")
  private String shortUrl;

  @OneToMany(mappedBy = "urlLinks")
  private List<Statistics> statistics;

  public URLinks(final String originalUrl, final String shortUrl) {
    this.originalUrl = originalUrl;
    this.shortUrl = shortUrl;
  }

  public URLinks() {
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public String getOriginalUrl() {
    return originalUrl;
  }

  public void setOriginalUrl(final String originalUrl) {
    this.originalUrl = originalUrl;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(final String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public List<Statistics> getStatistics() {
    return statistics;
  }

  public void setStatistics(final List<Statistics> statistics) {
    this.statistics = statistics;
  }
}

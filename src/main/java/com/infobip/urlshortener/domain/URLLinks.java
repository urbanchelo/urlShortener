package com.infobip.urlshortener.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "url_links")
public class URLLinks {

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

  public URLLinks(final String originalUrl, final String shortUrl) {
    this.originalUrl = originalUrl;
    this.shortUrl = shortUrl;
  }

}

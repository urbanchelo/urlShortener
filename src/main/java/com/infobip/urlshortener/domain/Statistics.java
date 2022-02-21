package com.infobip.urlshortener.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "statistics")
public class Statistics {

  @Id
  @Column(name = "id")
  @GeneratedValue(generator = "UUID")
  @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
  private String id;

  @ManyToOne
  @JoinColumn(name = "url_id", referencedColumnName = "id")
  private URLinks urlLinks;

  @Column(name = "calls_count")
  private int callCount;

  public Statistics() {
  }

  public Statistics(final URLinks urlLinks, final int callCount) {
    this.urlLinks = urlLinks;
    this.callCount = callCount;
  }

  public String getId() {
    return id;
  }

  public void setId(final String id) {
    this.id = id;
  }

  public Statistics(final URLinks urlLinks) {
    this.urlLinks = urlLinks;
  }

  public URLinks getUrlLinks() {
    return urlLinks;
  }

  public void setUrlLinks(final URLinks urlId) {
    this.urlLinks = urlId;
  }

  public int getCallCount() {
    return callCount;
  }

  public void setCallCount(final int callCount) {
    this.callCount = callCount;
  }

  public void increaseCallsCount() {
    this.callCount++;
  }
}

package com.infobip.urlshortener.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
  private URLLinks urlLinks;

  @Column(name = "calls_count")
  private int callCount;


  public Statistics(final URLLinks urlLinks) {
    this.urlLinks = urlLinks;
  }

  public void increaseCallsCount() {
    this.callCount++;
  }
}

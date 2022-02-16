package com.infobip.urlshortener.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "statistics")
public class Statistics {

  @EmbeddedId
  private StatisticsId statisticsId;

  @Column(name = "calls_count")
  private int callCount;

  public Statistics() {
  }

  public StatisticsId getUrlStatisticsId() {
    return statisticsId;
  }

  public void setUrlStatisticsId(final StatisticsId statisticsId) {
    this.statisticsId = statisticsId;
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

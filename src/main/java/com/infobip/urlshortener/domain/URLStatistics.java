package com.infobip.urlshortener.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "url_statistics")
public class URLStatistics {

  @EmbeddedId
  private URLStatisticsId urlStatisticsId;

  @Column(name = "calls_count")
  private int callCount;

  public URLStatistics() {
  }

  public URLStatisticsId getUrlStatisticsId() {
    return urlStatisticsId;
  }

  public void setUrlStatisticsId(final URLStatisticsId urlStatisticsId) {
    this.urlStatisticsId = urlStatisticsId;
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

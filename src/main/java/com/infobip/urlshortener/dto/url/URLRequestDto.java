package com.infobip.urlshortener.dto.url;

public class URLRequestDto {

  private String url;
  private Integer redirectType;

  public String getUrl() {
    return url;
  }

  public void setUrl(final String url) {
    this.url = url;
  }

  public Integer getRedirectType() {
    return redirectType;
  }

  public void setRedirectType(final Integer redirectType) {
    this.redirectType = redirectType;
  }
}

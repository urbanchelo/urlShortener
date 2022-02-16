package com.infobip.urlshortener.dto.url;

public class URLResponseDto {

  private String shortUrl;

  public URLResponseDto(final String shortUrl) {
    this.shortUrl = shortUrl;
  }

  public String getShortUrl() {
    return shortUrl;
  }

  public void setShortUrl(final String shortUrl) {
    this.shortUrl = shortUrl;
  }
}

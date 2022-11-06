package com.infobip.urlshortener.dto.url;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class URLRequestDto {

  private String url;
  private Integer redirectType;
}

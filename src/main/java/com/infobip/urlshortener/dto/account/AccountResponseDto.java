package com.infobip.urlshortener.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {

  private boolean success;
  private String description;

  @JsonInclude(NON_NULL)
  private String password;

}

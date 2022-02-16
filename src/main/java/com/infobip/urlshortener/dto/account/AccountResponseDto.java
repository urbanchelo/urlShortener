package com.infobip.urlshortener.dto.account;

import com.fasterxml.jackson.annotation.JsonInclude;
import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

public class AccountResponseDto {

  private boolean success;
  private String description;

  @JsonInclude(NON_NULL)
  private String password;

  public AccountResponseDto(final boolean success, final String description, final String password) {
    this.success = success;
    this.description = description;
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public boolean isSuccess() {
    return success;
  }

  public void setSuccess(final boolean success) {
    this.success = success;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(final String description) {
    this.description = description;
  }
}

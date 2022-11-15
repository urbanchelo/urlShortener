package com.infobip.urlshortener.controller;

import java.io.IOException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.infobip.urlshortener.dto.url.URLRequestDto;
import com.infobip.urlshortener.dto.url.URLResponseDto;
import com.infobip.urlshortener.service.ShortenerService;
import com.infobip.urlshortener.validator.RequestParamValidator;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import static org.springframework.http.HttpStatus.valueOf;

@RequiredArgsConstructor
@RestController
public class ShortenerController {

  private final ShortenerService shortenerService;
  private final RequestParamValidator paramValidator;

  @Operation(summary = "Endpoint for shortening of URLs")
  @PostMapping("/register")
  public ResponseEntity<URLResponseDto> shortenURL(@RequestBody URLRequestDto dto) {
    paramValidator.checkURLRequestBody(dto);
    return new ResponseEntity<>(shortenerService.shortentUrl(dto), valueOf(dto.getRedirectType()));
  }

  @Operation(summary = "Redirects to original url mapped to shorten URL", description = "Saves statistics of user usage")
  @GetMapping("/{uuid}")
  public void redirectToOriginalURL(@PathVariable String uuid, HttpServletResponse response) throws IOException {
    response.sendRedirect(shortenerService.getOriginalUrl(uuid));
  }

}

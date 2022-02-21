package com.infobip.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.infobip.urlshortener.domain.URLLinks;

public interface ShortenerRepository extends CrudRepository<URLLinks, String> {

  Optional<URLLinks> findByOriginalUrl(String originalUrl);

  Optional<URLLinks> findByShortUrl(String shortUrl);
}

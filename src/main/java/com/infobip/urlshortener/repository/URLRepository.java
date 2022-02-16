package com.infobip.urlshortener.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.infobip.urlshortener.domain.URLinks;

public interface URLRepository extends CrudRepository<URLinks, String> {

  Optional<URLinks> findByOriginalUrl(String originalUrl);

  Optional<URLinks> findByShortUrl(String shortUrl);
}

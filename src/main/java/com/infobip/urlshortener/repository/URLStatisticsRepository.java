package com.infobip.urlshortener.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infobip.urlshortener.domain.URLStatistics;

@Repository
public interface URLStatisticsRepository extends CrudRepository<URLStatistics, String> {

  @Query(value = "SELECT * " +
      "FROM url_statistics us " +
      "WHERE us.account_id = :accountId " +
      "AND us.url_id = :urlId", nativeQuery = true)
  Optional<URLStatistics> findByAccountIdAndUrlId(String accountId, String urlId);

  @Query(value = "SELECT * " +
      "FROM url_statistics us " +
      "WHERE us.account_id = :accountId ", nativeQuery = true)
  List<URLStatistics> findByAccountId(String accountId);
}

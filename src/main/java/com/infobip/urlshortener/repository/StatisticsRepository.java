package com.infobip.urlshortener.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infobip.urlshortener.domain.Statistics;

@Repository
public interface StatisticsRepository extends CrudRepository<Statistics, String> {

  @Query(value = "SELECT * " +
      "FROM statistics us " +
      "WHERE us.account_id = :accountId " +
      "AND us.url_id = :urlId", nativeQuery = true)
  Optional<Statistics> findByAccountIdAndUrlId(String accountId, String urlId);

  @Query(value = "SELECT * " +
      "FROM statistics us " +
      "WHERE us.account_id = :accountId ", nativeQuery = true)
  List<Statistics> findByAccountId(String accountId);
}

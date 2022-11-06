package com.infobip.urlshortener.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.infobip.urlshortener.domain.Account;

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

}

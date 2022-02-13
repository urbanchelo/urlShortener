package com.infobip.urlshortener.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.infobip.urlshortener.domain.Account;

public interface AccountRepository extends CrudRepository<Account, String> {

}

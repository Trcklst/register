package com.trcklst.register.core.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface AccountRepository extends MongoRepository<Account, Integer> {

    Optional<Account> findFirstByOrderByIdDesc();
}

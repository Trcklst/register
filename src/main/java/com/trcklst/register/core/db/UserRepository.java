package com.trcklst.register.core.db;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Integer> {

    Optional<User> findFirstByOrderByIdDesc();

    boolean existsByUsername(String username);
}

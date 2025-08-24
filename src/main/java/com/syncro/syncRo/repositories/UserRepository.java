package com.syncro.syncro.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.syncro.syncro.models.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByUsername(String username);
}

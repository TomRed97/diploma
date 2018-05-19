package com.diploma.easyscraper.interfaces;

import com.diploma.easyscraper.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    Optional<User> findByLogin(String login);

    Optional<User> findByEmail(String email);
}

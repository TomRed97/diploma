package com.diploma.easyscraper.interfaces;

import com.diploma.easyscraper.model.User;

import java.util.Optional;

public interface UserService {

    void save(User user);

    Optional<User> findUserByLogin(String login);

    Optional<User> findUserByEmail(String login);
}

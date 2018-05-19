package com.diploma.easyscraper.service;

import com.diploma.easyscraper.interfaces.UserRepository;
import com.diploma.easyscraper.interfaces.UserService;
import com.diploma.easyscraper.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service()
public class UserServiceImpl implements UserService {

    @Autowired()
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public Optional<User> findUserByLogin(String login) {
        return this.userRepository.findByLogin(login);
    }

    @Override
    public Optional<User> findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}

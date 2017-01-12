package com.aabramov.service.entities.impl;

import com.aabramov.service.security.PasswordService;
import com.aabramov.settings.logger.mediator.LoggingMediator;
import com.aabramov.model.User;
import com.aabramov.repository.UserRepository;
import com.aabramov.service.entities.UserService;
import com.aabramov.settings.logger.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private LoggingMediator LOGGER;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordService passwordService;

    @Override
    public User authenticate(String username, String password) {

        final User user = userRepository.findFirstByUsername(username);

        if (passwordService.matches(password, user.getPasswordHash()))
            return user;
        else
            return null;
    }

    @Override
    public User addNewUser(User user) {
        Objects.requireNonNull(user);
        LOGGER.log(Logger.Level.INFO, "Adding new User: " + user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        Objects.requireNonNull(user);
        LOGGER.log(Logger.Level.INFO, "Updating User: " + user.getUsername());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        LOGGER.log(Logger.Level.INFO, "Deleting User: " + user.getUsername());
        userRepository.delete(user);
    }

    @Override
    public boolean userExists(String username) {
        LOGGER.log(Logger.Level.INFO, "Checking whether the user exists: " + username);
        return userRepository.findFirstByUsername(username) != null;
    }

    @Override
    public List<User> getAll() {
        LOGGER.log(Logger.Level.INFO, "Fetching all Users.");
        return userRepository.findAll();
    }
}

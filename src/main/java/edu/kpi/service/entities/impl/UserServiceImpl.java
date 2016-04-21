package edu.kpi.service.entities.impl;

import edu.kpi.model.User;
import edu.kpi.repository.UserRepository;
import edu.kpi.service.entities.UserService;
import edu.kpi.service.security.PasswordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

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
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findFirstByUsername(username) != null;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}

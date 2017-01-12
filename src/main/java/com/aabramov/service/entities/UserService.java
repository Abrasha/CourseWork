package com.aabramov.service.entities;

import com.aabramov.model.User;

import java.util.List;

public interface UserService {

    User authenticate(String username, String password);

    User addNewUser(User user);

    User updateUser(User user);

    void deleteUser(User user);

    boolean userExists(String username);

    List<User> getAll();

}

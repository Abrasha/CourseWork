package com.aabramov.repository;

import com.aabramov.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findFirstByUsername(String username);

    @Override
    List<User> findAll();


}

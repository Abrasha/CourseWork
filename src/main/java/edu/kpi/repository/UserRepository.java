package edu.kpi.repository;

import edu.kpi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {

    User findFirstByUsername(String username);

    @Override
    List<User> findAll();


}

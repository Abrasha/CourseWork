package com.aabramov.repository;

import com.aabramov.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();
}

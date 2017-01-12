package com.aabramov.service.entities;

import com.aabramov.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee save(Employee employee);

    void remove(Employee employee);

    Employee update(Employee employee);

}

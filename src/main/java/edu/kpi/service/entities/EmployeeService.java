package edu.kpi.service.entities;

import edu.kpi.model.Employee;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();

    Employee save(Employee employee);

    void remove(Employee employee);

}

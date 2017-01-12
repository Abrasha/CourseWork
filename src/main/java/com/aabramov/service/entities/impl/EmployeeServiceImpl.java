package com.aabramov.service.entities.impl;

import com.aabramov.model.Employee;
import com.aabramov.repository.EmployeeRepository;
import com.aabramov.service.entities.EmployeeService;
import com.aabramov.settings.logger.Logger;
import com.aabramov.settings.logger.mediator.LoggingMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    
    @Autowired
    private EmployeeRepository repository;
    
    @Autowired
    private LoggingMediator LOGGER;
    
    @Override
    public List<Employee> findAll() {
        LOGGER.log(Logger.Level.INFO, "Searching for all employees.");
        return repository.findAll();
    }
    
    @Override
    public Employee save(Employee employee) {
        final Employee result = repository.save(employee);
        LOGGER.log(Logger.Level.INFO, "Employee inserted: " + result.getId());
        return result;
    }
    
    @Override
    public void remove(Employee employee) {
        LOGGER.log(Logger.Level.INFO, "Employee to remove: " + employee.getId());
        repository.delete(employee);
    }
    
    @Override
    public Employee update(Employee employee) {
        LOGGER.log(Logger.Level.INFO, "Updating Employee with id: " + employee.getId());
        return repository.save(employee);
    }
    
}

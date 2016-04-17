package edu.kpi.repository;

import edu.kpi.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(propagation = Propagation.MANDATORY)
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();
}

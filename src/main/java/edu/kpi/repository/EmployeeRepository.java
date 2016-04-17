package edu.kpi.repository;

import edu.kpi.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Transactional(propagation = Propagation.MANDATORY)
public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    List<Employee> findAll();
}

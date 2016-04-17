package edu.kpi.service;

import edu.kpi.model.Employee;
import edu.kpi.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public void remove(Employee employee) {
        repository.delete(employee);
    }

    @PostConstruct
    public void generateTestData() {
        save(new Employee.EmployeeBuilder()
                .setFName("Andrew")
                .setLName("Abramov")
                .setPhone("9949464")
                .setPassportId("WF 124124")
                .build()
        );
        save(new Employee.EmployeeBuilder()
                .setFName("Kirill")
                .setLName("Ryablo")
                .setPhone("2352423")
                .setPassportId("AG 998654")
                .build()
        );
    }

}

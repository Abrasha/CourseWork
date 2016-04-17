package edu.kpi.service;

import edu.kpi.model.Employee;
import edu.kpi.repository.EmployeeRepository;
import edu.kpi.settings.logger.Logger;
import edu.kpi.settings.logger.mediator.LoggingMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    @Qualifier("loggingMediator")
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

//    @PostConstruct
//    public void generateTestData() {
//        save(new Employee.EmployeeBuilder()
//                .setFName("Andrew")
//                .setLName("Abramov")
//                .setPhone("9949464")
//                .setPassportId("WF 124124")
//                .build()
//        );
//        save(new Employee.EmployeeBuilder()
//                .setFName("Kirill")
//                .setLName("Ryablo")
//                .setPhone("2352423")
//                .setPassportId("AG 998654")
//                .build()
//        );
//        LOGGER.log(Logger.Level.INFO, "Test data generated.");
//    }

}

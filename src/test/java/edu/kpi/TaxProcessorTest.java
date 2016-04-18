package edu.kpi;

import edu.kpi.model.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaxProcessorTest {

    @Test
    public void testSingleTax() {

        final Employee e1 = new Employee.EmployeeBuilder()
                .setFName("Andrew")
                .setLName("Abramov")
                .setPhone("9949464")
                .setPassportId("WF 124124")
                .build();

        final Employee e2 = new Employee.EmployeeBuilder()
                .setFName("Kirill")
                .setLName("Ryablo")
                .setPhone("2352423")
                .setPassportId("AG 998654")
                .build();
        final Employee e3 = new Employee.EmployeeBuilder()
                .setFName("John")
                .setLName("Snow")
                .setPhone("2365235")
                .setPassportId("LH 897975")
                .build();

        final List<Employee> employees = Arrays.asList(e1, e2, e3);

        final Map<Employee, Integer> employeesIncome = employees.stream()
                .collect(
                        Collectors.toMap(
                                e -> e, (e -> (int) (10000 * Math.random()))
                        )
                );

//        final Map<Employee, TaxReport> reports = TaxProcessor.calculateTax(
//                new tax, new SingleTax()
//        );
//
//        reports.forEach((employee, report) -> {
//            System.out.println(employee.getFirstName() + ": " + report);
//        });


    }


    @Test
    public void testOnProfitTax() {
        final Employee e1 = new Employee.EmployeeBuilder()
                .setFName("Andrew")
                .setLName("Abramov")
                .setPhone("9949464")
                .setPassportId("WF 124124")
                .build();

        final Employee e2 = new Employee.EmployeeBuilder()
                .setFName("Kirill")
                .setLName("Ryablo")
                .setPhone("2352423")
                .setPassportId("AG 998654")
                .build();
        final Employee e3 = new Employee.EmployeeBuilder()
                .setFName("John")
                .setLName("Snow")
                .setPhone("2365235")
                .setPassportId("LH 897975")
                .build();

        final List<Employee> employees = Arrays.asList(e1, e2, e3);

        final Map<Employee, Integer> employeesIncome = employees.stream()
                .collect(
                        Collectors.toMap(
                                e -> e, (e -> (int) (10000 * Math.random()))
                        )
                );

//        final Map<Employee, TaxReport> reports = TaxProcessor.calculateTax(
//                employeesIncome, new TaxOnProfits()
//        );
//
//        reports.forEach((employee, report) -> {
//            System.out.println(employee.getFirstName() + ": " + report);
//        });
    }
}

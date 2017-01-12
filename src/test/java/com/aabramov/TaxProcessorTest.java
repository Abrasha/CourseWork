package com.aabramov;

import com.aabramov.model.Employee;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TaxProcessorTest {

    @Test
    public void testSingleTax() {

        final Employee e1 = new Employee.EmployeeBuilder()
                .withFirstName("Andrew")
                .withLastName("Abramov")
                .withPhone("9949464")
                .withPassportId("WF 124124")
                .build();

        final Employee e2 = new Employee.EmployeeBuilder()
                .withFirstName("Kirill")
                .withLastName("Ryablo")
                .withPhone("2352423")
                .withPassportId("AG 998654")
                .build();
        final Employee e3 = new Employee.EmployeeBuilder()
                .withFirstName("John")
                .withLastName("Snow")
                .withPhone("2365235")
                .withPassportId("LH 897975")
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
                .withFirstName("Andrew")
                .withLastName("Abramov")
                .withPhone("9949464")
                .withPassportId("WF 124124")
                .build();

        final Employee e2 = new Employee.EmployeeBuilder()
                .withFirstName("Kirill")
                .withLastName("Ryablo")
                .withPhone("2352423")
                .withPassportId("AG 998654")
                .build();
        final Employee e3 = new Employee.EmployeeBuilder()
                .withFirstName("John")
                .withLastName("Snow")
                .withPhone("2365235")
                .withPassportId("LH 897975")
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

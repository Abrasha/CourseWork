package edu.kpi.service.tax;

import edu.kpi.model.Employee;
import edu.kpi.model.TaxReport;

@FunctionalInterface
public interface TaxCalculator {

    TaxReport processIncome(int income, Employee forEmployee);

}

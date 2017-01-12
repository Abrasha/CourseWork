package com.aabramov.service.tax;

import com.aabramov.model.Employee;
import com.aabramov.model.TaxReport;

@FunctionalInterface
public interface TaxCalculator {

    TaxReport processIncome(int income, Employee forEmployee);

}

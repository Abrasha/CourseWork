package com.aabramov.service.tax.impl;

import com.aabramov.model.Employee;
import com.aabramov.model.TaxReport;
import com.aabramov.service.tax.TaxCalculator;

public class SingleTax implements TaxCalculator {
    
    public static final float SINGLE_TAX_RATE = 0.12f;
    
    @Override
    public TaxReport processIncome(int income, Employee forEmployee) {
        
        int tax = Math.round(income * SINGLE_TAX_RATE);
        int profit = income - tax;
        return new TaxReport(income, tax, profit, forEmployee);
        
    }
}

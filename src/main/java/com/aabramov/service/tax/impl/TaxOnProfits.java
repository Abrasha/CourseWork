package com.aabramov.service.tax.impl;

import com.aabramov.model.TaxReport;
import com.aabramov.model.Employee;
import com.aabramov.service.tax.TaxCalculator;

/**
 * @author Andrii Abramov on 18-Apr-16.
 */
public class TaxOnProfits implements TaxCalculator {
    
    public static final float BASE_TAX_RATE = 0.08f;
    public static final float PROFIT_TAX_RATE = 0.04f;
    
    @Override
    public TaxReport processIncome(int income, Employee forEmployee) {
        
        int tax = Math.round((income - getBaseTax(income)) * PROFIT_TAX_RATE);
        int profit = income - tax;
        return new TaxReport(income, tax, profit, forEmployee);
        
    }
    
    private float getBaseTax(int income) {
        return income * BASE_TAX_RATE;
    }
    
}

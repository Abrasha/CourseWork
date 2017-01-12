package com.aabramov.service.tax.impl;

import com.aabramov.model.TaxReport;
import com.aabramov.service.tax.TaxCalculator;

import java.util.List;
import java.util.stream.Collectors;

public class TaxProcessor {
    
    public static List<TaxReport> calculateTax(final List<TaxReport> reports, final TaxCalculator taxCalculator) {
        
        return reports.stream()
                .map(e -> taxCalculator.processIncome(e.getIncome(), e.getForEmployee()))
                .collect(Collectors.toList());
        
    }
    
    public enum TaxType {
        SINGLE_TAX, PROFIT_TASK
    }
    
}

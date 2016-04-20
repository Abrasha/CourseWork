package edu.kpi.service.tax.impl;

import edu.kpi.model.TaxReport;
import edu.kpi.service.tax.TaxCalculator;

import java.util.List;
import java.util.stream.Collectors;

// TODO Strategy Pattern
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

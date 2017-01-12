package com.aabramov.service.entities;

import com.aabramov.model.TaxReport;

import java.time.Month;
import java.util.List;

public interface TaxReportsService {

    List<TaxReport> getForMonth(Month month);

    void removeAllWithinMonth(Month month);

    void update(List<TaxReport> reports);

    void update(TaxReport... reports);
}

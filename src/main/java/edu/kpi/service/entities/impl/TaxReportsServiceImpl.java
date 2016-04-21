package edu.kpi.service.entities.impl;

import edu.kpi.model.TaxReport;
import edu.kpi.repository.TaxReportsRepository;
import edu.kpi.service.entities.TaxReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TaxReportsServiceImpl implements TaxReportsService {

    @Autowired
    private TaxReportsRepository repository;

    @Override
    public List<TaxReport> getForMonth(Month month) {
        return repository.findByMonth(month);
    }

    @Override
    public void removeAllWithinMonth(Month month) {
        repository.findByMonth(month)
                .forEach(repository::delete);
    }

    @Override
    public void update(List<TaxReport> reports) {
        reports.forEach(repository::save);
    }

    @Override
    public void update(TaxReport... reports) {
        Stream.of(reports).forEach(repository::save);
    }
}

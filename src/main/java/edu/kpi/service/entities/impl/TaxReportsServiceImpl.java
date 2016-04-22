package edu.kpi.service.entities.impl;

import edu.kpi.model.TaxReport;
import edu.kpi.repository.TaxReportsRepository;
import edu.kpi.service.entities.TaxReportsService;
import edu.kpi.settings.logger.Logger;
import edu.kpi.settings.logger.mediator.LoggingMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.List;
import java.util.stream.Stream;

@Service
public class TaxReportsServiceImpl implements TaxReportsService {

    @Autowired
    private LoggingMediator LOGGER;

    @Autowired
    private TaxReportsRepository repository;

    @Override
    public List<TaxReport> getForMonth(Month month) {
        LOGGER.log(Logger.Level.INFO, "Getting TaxReports by Month: " + month);
        return repository.findByMonth(month);
    }

    @Override
    public void removeAllWithinMonth(Month month) {
        LOGGER.log(Logger.Level.INFO, "Removing TaxReports by Month: " + month);
        repository.findByMonth(month)
                .forEach(repository::delete);
    }

    @Override
    public void update(List<TaxReport> reports) {

        LOGGER.log(Logger.Level.INFO, String.format("Updating %d TaxReport(s)", reports.size()));
        reports.forEach(repository::save);
    }

    @Override
    public void update(TaxReport... reports) {

        LOGGER.log(Logger.Level.INFO, String.format("Updating %d TaxReport(s)", reports.length));
        Stream.of(reports).forEach(repository::save);
    }
}

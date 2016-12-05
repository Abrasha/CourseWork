package edu.kpi.service.entities.impl;

import edu.kpi.model.TaxReport;
import edu.kpi.repository.TaxReportsRepository;
import edu.kpi.service.entities.TaxReportsService;
import edu.kpi.settings.logger.Logger;
import edu.kpi.settings.logger.mediator.LoggingMediator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

@Service
@Transactional
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
        repository.save(reports);
    }
    
    @Override
    public void update(TaxReport... reports) {
        LOGGER.log(Logger.Level.INFO, String.format("Updating %d TaxReport(s)", reports.length));
        repository.save(Arrays.asList(reports));
    }
}

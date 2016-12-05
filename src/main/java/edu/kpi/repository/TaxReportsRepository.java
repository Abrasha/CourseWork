package edu.kpi.repository;

import edu.kpi.model.TaxReport;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Month;
import java.util.List;

@Repository
public interface TaxReportsRepository extends CrudRepository<TaxReport, Long> {
    List<TaxReport> findByMonth(Month month);
}

package com.aabramov.model;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Month;

@Entity
@Table(name = "tax_reports")
public class TaxReport implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    private int income;
    private int tax;
    private int profit;

    @Enumerated(EnumType.ORDINAL)
    private Month month;

    @ManyToOne
    private Employee forEmployee;

    public TaxReport(int income, int tax, int profit, Employee forEmployee) {
        this.income = income;
        this.tax = tax;
        this.profit = profit;
        this.forEmployee = forEmployee;
    }

    public TaxReport(Employee forEmployee) {
        this(0, 0, 0, forEmployee);
    }

    public TaxReport() {
    }


    public Month getMonth() {
        return month;
    }

    public void setMonth(Month month) {
        this.month = month;
    }

    public Employee getForEmployee() {
        return forEmployee;
    }

    public void setForEmployee(Employee forEmployee) {
        this.forEmployee = forEmployee;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getIncome() {
        return income;
    }

    public void setIncome(int income) {
        this.income = income;
    }

    public int getTax() {
        return tax;
    }

    public void setTax(int tax) {
        this.tax = tax;
    }

    public int getProfit() {
        return profit;
    }

    public void setProfit(int profit) {
        this.profit = profit;
    }

    public String getLastName() {
        return forEmployee.getLastName();
    }

    public String getPassportId() {
        return forEmployee.getPassportId();
    }

    @Override
    public String toString() {
        return "TaxReport{" +
                "id=" + id +
                ", income=" + income +
                ", tax=" + tax +
                ", profit=" + profit +
                ", month=" + month +
                ", forEmployee=" + forEmployee +
                '}';
    }
}

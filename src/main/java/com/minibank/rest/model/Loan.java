package com.minibank.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minibank.common.DateTimeParameters;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Loan {

    private Integer id;

    private BigDecimal currInterestRate;

    private BigDecimal currInterest;

    private BigDecimal amount;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeParameters.DATE_FORMAT)
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeParameters.DATE_FORMAT)
    private LocalDate endDate;

    private List<LoanExtension> loanExtensions = new ArrayList<>();

    public Loan() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigDecimal getCurrInterestRate() {
        return currInterestRate;
    }

    public void setCurrInterestRate(BigDecimal currInterestRate) {
        this.currInterestRate = currInterestRate;
    }

    public BigDecimal getCurrInterest() {
        return currInterest;
    }

    public void setCurrInterest(BigDecimal currInterest) {
        this.currInterest = currInterest;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public List<LoanExtension> getLoanExtensions() {
        return loanExtensions;
    }

    public void setLoanExtensions(List<LoanExtension> loanExtensions) {
        this.loanExtensions = loanExtensions;
    }
}

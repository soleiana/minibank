package com.minibank.communications.model;

import java.math.BigDecimal;
import java.time.LocalDate;


public class LoanExtension {

    private BigDecimal interestRate;
    private BigDecimal interest;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate submissionDate;

    public LoanExtension() {}

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}

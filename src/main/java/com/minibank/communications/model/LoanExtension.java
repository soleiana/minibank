package com.minibank.communications.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.time.LocalDate;


public class LoanExtension {

    private BigDecimal interestRate;
    private BigDecimal interest;
    private Date startDate;
    private Date endDate;
    private LocalDate submissionDate;

    public LoanExtension() {}

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}

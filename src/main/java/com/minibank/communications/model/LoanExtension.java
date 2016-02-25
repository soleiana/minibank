package com.minibank.communications.model;

import java.math.BigDecimal;
import java.sql.Date;


public class LoanExtension {

    private BigDecimal interestRate;
    private BigDecimal interest;
    private Date startDate;
    private Date endDate;
    private Date submissionDate;

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

    public Date getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate) {
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

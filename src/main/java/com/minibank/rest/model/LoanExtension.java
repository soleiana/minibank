package com.minibank.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minibank.common.DateTimeParameters;

import java.math.BigDecimal;
import java.time.LocalDate;


public class LoanExtension {

    private BigDecimal interestRate;

    private BigDecimal interest;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeParameters.DATE_FORMAT)
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeParameters.DATE_FORMAT)
    private LocalDate endDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeParameters.DATE_FORMAT)
    private LocalDate submissionDate;


    public LoanExtension() {}

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
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

    public LocalDate getSubmissionDate() {
        return submissionDate;
    }

    public void setSubmissionDate(LocalDate submissionDate) {
        this.submissionDate = submissionDate;
    }
}

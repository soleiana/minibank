package com.minibank.rest.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.minibank.common.DateTimeParameters;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


public class Loan {

    @NotNull
    private Integer id;

    @NotNull
    private BigDecimal currInterestRate;

    @NotNull
    private BigDecimal currInterest;

    @NotNull
    private BigDecimal amount;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeParameters.DATE_FORMAT)
    private LocalDate startDate;

    @NotNull
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateTimeParameters.DATE_FORMAT)
    private LocalDate endDate;

    private List<LoanExtension> loanExtensions = new ArrayList<>();

    public Loan() {}

    public BigDecimal getCurrInterestRate() {
        return currInterestRate;
    }

    public BigDecimal getCurrInterest() {
        return currInterest;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<LoanExtension> getLoanExtensions() {
        return loanExtensions;
    }

    public void setLoanExtensions(List<LoanExtension> loanExtensions) {
        this.loanExtensions = loanExtensions;
    }

    public void setCurrInterestRate(BigDecimal currInterestRate) {
        this.currInterestRate = currInterestRate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setCurrInterest(BigDecimal currInterest) {
        this.currInterest = currInterest;
    }
}

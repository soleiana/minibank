package com.minibank.core.communications.loans.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ann on 06/09/14.
 */
public class Loan
{
    private Integer id;
    private BigDecimal currInterestRate;
    private BigDecimal currInterest;
    private BigDecimal amount;
    private Date startDate;
    private Date endDate;
    private List<LoanExtension> loanExtensions = new ArrayList<>();

    public Loan()
    {}

    public BigDecimal getCurrInterestRate()
    {
        return currInterestRate;
    }

    public BigDecimal getCurrInterest()
    {
        return currInterest;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public List<LoanExtension> getLoanExtensions()
    {
        return loanExtensions;
    }

    public void setLoanExtensions(List<LoanExtension> loanExtensions)
    {
        this.loanExtensions = loanExtensions;
    }

    public void setCurrInterestRate(BigDecimal currInterestRate)
    {
        this.currInterestRate = currInterestRate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public void setCurrInterest(BigDecimal currInterest)
    {
        this.currInterest = currInterest;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Loan loan = (Loan) o;

        if (amount != null ? !amount.equals(loan.amount) : loan.amount != null) return false;
        if (currInterest != null ? !currInterest.equals(loan.currInterest) : loan.currInterest != null) return false;
        if (currInterestRate != null ? !currInterestRate.equals(loan.currInterestRate) : loan.currInterestRate != null)
            return false;
        if (endDate != null ? !endDate.equals(loan.endDate) : loan.endDate != null) return false;
        if (id != null ? !id.equals(loan.id) : loan.id != null) return false;
        if (loanExtensions != null ? !loanExtensions.equals(loan.loanExtensions) : loan.loanExtensions != null)
            return false;
        if (startDate != null ? !startDate.equals(loan.startDate) : loan.startDate != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (currInterestRate != null ? currInterestRate.hashCode() : 0);
        result = 31 * result + (currInterest != null ? currInterest.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (loanExtensions != null ? loanExtensions.hashCode() : 0);
        return result;
    }
}

package com.minibank.core.communications.loans.domain;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Ann on 14/09/14.
 */
public class LoanExtension
{
    private Integer loanId;
    private BigDecimal interestRate;
    private BigDecimal interest;
    private Date startDate;
    private Date endDate;
    private Date submissionDate;

    public LoanExtension()
    {}

    public Integer getLoanId()
    {
        return loanId;
    }

    public BigDecimal getInterestRate()
    {
        return interestRate;
    }

    public BigDecimal getInterest()
    {
        return interest;
    }

    public Date getStartDate()
    {
        return startDate;
    }

    public Date getEndDate()
    {
        return endDate;
    }

    public Date getSubmissionDate()
    {
        return submissionDate;
    }

    public void setLoanId(Integer loanId)

    {
        this.loanId = loanId;
    }

    public void setSubmissionDate(Date submissionDate)
    {
        this.submissionDate = submissionDate;
    }

    public void setEndDate(Date endDate)
    {
        this.endDate = endDate;
    }

    public void setStartDate(Date startDate)
    {
        this.startDate = startDate;
    }

    public void setInterest(BigDecimal interest)
    {
        this.interest = interest;
    }

    public void setInterestRate(BigDecimal interestRate)
    {
        this.interestRate = interestRate;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanExtension that = (LoanExtension) o;

        if (endDate != null ? !endDate.equals(that.endDate) : that.endDate != null) return false;
        if (interest != null ? !interest.equals(that.interest) : that.interest != null) return false;
        if (interestRate != null ? !interestRate.equals(that.interestRate) : that.interestRate != null) return false;
        if (loanId != null ? !loanId.equals(that.loanId) : that.loanId != null) return false;
        if (startDate != null ? !startDate.equals(that.startDate) : that.startDate != null) return false;
        if (submissionDate != null ? !submissionDate.equals(that.submissionDate) : that.submissionDate != null)
            return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = loanId != null ? loanId.hashCode() : 0;
        result = 31 * result + (interestRate != null ? interestRate.hashCode() : 0);
        result = 31 * result + (interest != null ? interest.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        result = 31 * result + (submissionDate != null ? submissionDate.hashCode() : 0);
        return result;
    }
}

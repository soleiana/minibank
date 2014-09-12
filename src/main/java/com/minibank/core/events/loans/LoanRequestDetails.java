package com.minibank.core.events.loans;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Ann on 12/09/14.
 */
public class LoanRequestDetails
{
    private Integer id;
    private Integer loan_id;
    private Integer customer_id;
    private String requestIP;
    private Date submissionDate;
    private Time submissionTime;
    private Integer term;
    private BigDecimal amount;
    private String status;

    public LoanRequestDetails()
    {
        id = null;
    }

    public Integer getId()
    {
        return id;
    }

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getLoan_id()
    {
        return loan_id;
    }

    public void setLoan_id(Integer loan_id)
    {
        this.loan_id = loan_id;
    }

    public Integer getCustomer_id()
    {
        return customer_id;
    }

    public void setCustomer_id(Integer customer_id)
    {
        this.customer_id = customer_id;
    }

    public String getRequestIP()
    {
        return requestIP;
    }

    public void setRequestIP(String requestIP)
    {
        this.requestIP = requestIP;
    }

    public Date getSubmissionDate()
    {
        return submissionDate;
    }

    public void setSubmissionDate(Date submissionDate)
    {
        this.submissionDate = submissionDate;
    }

    public Time getSubmissionTime()
    {
        return submissionTime;
    }

    public void setSubmissionTime(Time submissionTime)
    {
        this.submissionTime = submissionTime;
    }

    public Integer getTerm()
    {
        return term;
    }

    public void setTerm(Integer term)
    {
        this.term = term;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }
}

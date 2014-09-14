package com.minibank.core.events.loans.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;


/**
 * Created by Ann on 12/09/14.
 */
public class LoanRequestDetails
{
    private Integer customerId;
    private String requestIP;
    private Integer term;
    private BigDecimal amount;

    public LoanRequestDetails()
    {}

    public Integer getCustomerId()
    {
        return customerId;
    }

    public String getRequestIP()
    {
        return requestIP;
    }

    public Integer getTerm()
    {
        return term;
    }

    public BigDecimal getAmount()
    {
        return amount;
    }

    public void setCustomerId(Integer customerId)
    {
        this.customerId = customerId;
    }

    public void setRequestIP(String requestIP)
    {
        this.requestIP = requestIP;
    }

    public void setTerm(Integer term)
    {
        this.term = term;
    }

    public void setAmount(BigDecimal amount)
    {
        this.amount = amount;
    }
}

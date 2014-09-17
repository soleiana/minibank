package com.minibank.rest.domain;

import java.math.BigDecimal;

/**
 * Created by Ann on 07/09/14.
 */
public class LoanRequest
{
    private Integer customerId;
    private String requestIP;
    private Integer term;
    private BigDecimal amount;

    public LoanRequest()
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

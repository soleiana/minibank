package com.minibank.core.events.loans.domain;

/**
 * Created by Ann on 14/09/14.
 */
public class RequestAllLoansDetails
{
    private Integer customerId;

    public RequestAllLoansDetails(Integer customerId)
    {
        this.customerId = customerId;
    }

    public Integer getCustomerId()
    {
        return customerId;
    }
}

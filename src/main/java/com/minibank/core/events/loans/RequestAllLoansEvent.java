package com.minibank.core.events.loans;

import com.minibank.core.events.RequestReadEvent;

/**
 * Created by Ann on 06/09/14.
 */
public class RequestAllLoansEvent extends RequestReadEvent
{
    private final Integer customerId;

    public RequestAllLoansEvent(Integer customerId)
    {
        this.customerId = customerId;
    }

    public Integer getCustomerId()
    {
        return customerId;
    }
}

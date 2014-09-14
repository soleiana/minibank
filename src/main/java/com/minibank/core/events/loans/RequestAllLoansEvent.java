package com.minibank.core.events.loans;

import com.minibank.core.events.RequestReadEvent;
import com.minibank.core.events.loans.domain.RequestAllLoansDetails;

/**
 * Created by Ann on 06/09/14.
 */
public class RequestAllLoansEvent extends RequestReadEvent
{
    private RequestAllLoansDetails requestAllLoansDetails;

    public RequestAllLoansEvent(RequestAllLoansDetails requestAllLoansDetails)
    {
        this.requestAllLoansDetails = requestAllLoansDetails;
    }

    public RequestAllLoansDetails getRequestAllLoansDetails()
    {
        return requestAllLoansDetails;
    }
}

package com.minibank.core.events.loans;

import com.minibank.core.events.CreatedEvent;

/**
 * Created by Ann on 06/09/14.
 */
public class LoanCreatedEvent extends CreatedEvent
{
    private final Integer loanRequestId;
    private final LoanRequestDetails loanRequestDetails;

    public LoanCreatedEvent(final Integer loanRequestId,
                            final LoanRequestDetails loanRequestDetails)
    {
        this.loanRequestId = loanRequestId;
        this.loanRequestDetails = loanRequestDetails;
    }

    public Integer getLoanRequestId()
    {
        return loanRequestId;
    }

    public LoanRequestDetails getLoanRequestDetails()
    {
        return loanRequestDetails;
    }
}

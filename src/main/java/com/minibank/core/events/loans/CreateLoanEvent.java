package com.minibank.core.events.loans;

import com.minibank.core.events.CreateEvent;

/**
 * Created by Ann on 06/09/14.
 */
public class CreateLoanEvent extends CreateEvent
{
    private LoanRequestDetails loanRequestDetails;

    public CreateLoanEvent(LoanRequestDetails loanRequestDetails)
    {
        this.loanRequestDetails = loanRequestDetails;
    }

    public LoanRequestDetails getLoanRequestDetails()
    {
        return loanRequestDetails;
    }
}

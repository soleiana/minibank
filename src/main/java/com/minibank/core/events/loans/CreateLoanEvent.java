package com.minibank.core.events.loans;

import com.minibank.core.events.CreateEvent;
import com.minibank.core.events.loans.domain.LoanRequestDetails;

/**
 * Created by Ann on 06/09/14.
 */
public class CreateLoanEvent extends CreateEvent
{
    private final LoanRequestDetails loanRequestDetails;

    public CreateLoanEvent(LoanRequestDetails loanRequestDetails)
    {
        this.loanRequestDetails = loanRequestDetails;
    }

    public LoanRequestDetails getLoanRequestDetails()
    {
        return loanRequestDetails;

    }

}

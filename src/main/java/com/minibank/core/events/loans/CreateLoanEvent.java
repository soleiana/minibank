package com.minibank.core.events.loans;

import com.minibank.core.events.CreateEvent;
import com.minibank.core.events.loans.domain.LoanRequestDetails;

/**
 * Created by Ann on 06/09/14.
 */
public class CreateLoanEvent extends CreateEvent
{
    private LoanRequestDetails loanRequestDetails;

    public CreateLoanEvent()
    {}

    public CreateLoanEvent(LoanRequestDetails loanRequestDetails)
    {
        this.loanRequestDetails = loanRequestDetails;
    }

    public LoanRequestDetails getLoanRequestDetails()
    {
        return loanRequestDetails;

    }

    public void setLoanRequestDetails(LoanRequestDetails loanRequestDetails)
    {
        this.loanRequestDetails = loanRequestDetails;
    }
}

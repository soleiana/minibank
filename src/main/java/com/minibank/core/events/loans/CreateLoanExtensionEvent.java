package com.minibank.core.events.loans;

import com.minibank.core.events.CreateEvent;
import com.minibank.core.events.loans.domain.LoanExtensionDetails;

/**
 * Created by Ann on 06/09/14.
 */
public class CreateLoanExtensionEvent extends CreateEvent
{
    private LoanExtensionDetails loanExtensionDetails;

    public CreateLoanExtensionEvent()
    {}

    public CreateLoanExtensionEvent(LoanExtensionDetails loanExtensionDetails)
    {
        this.loanExtensionDetails = loanExtensionDetails;
    }

    public LoanExtensionDetails getLoanExtensionDetails()
    {
        return loanExtensionDetails;
    }

    public void setLoanExtensionDetails(LoanExtensionDetails loanExtensionDetails)
    {
        this.loanExtensionDetails = loanExtensionDetails;
    }
}

package com.minibank.core.events.loans;

import com.minibank.core.events.CreatedEvent;

/**
 * Created by Ann on 06/09/14.
 */
public class LoanCreatedEvent extends CreatedEvent
{
    private Boolean loanObtained;
    private String errorMessage;

    public LoanCreatedEvent(Boolean loanObtained, String errorMessage)
    {
        this.loanObtained = loanObtained;
        this.errorMessage = errorMessage;
    }

    public Boolean isLoanObtained()
    {
        return loanObtained;
    }

    public String getErrorMessage()
    {
        return errorMessage;
    }
}

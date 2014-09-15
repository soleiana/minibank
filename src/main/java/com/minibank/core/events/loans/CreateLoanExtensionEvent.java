package com.minibank.core.events.loans;

import com.minibank.core.events.CreateEvent;

/**
 * Created by Ann on 06/09/14.
 */
public class CreateLoanExtensionEvent extends CreateEvent
{
    private Integer loanId;

    public CreateLoanExtensionEvent(Integer loanId)
    {
        this.loanId = loanId;
    }

    public Integer getLoanId()
    {
        return loanId;
    }
}

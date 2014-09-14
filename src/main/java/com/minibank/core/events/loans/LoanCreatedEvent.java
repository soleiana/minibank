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

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanCreatedEvent that = (LoanCreatedEvent) o;

        if (errorMessage != null ? !errorMessage.equals(that.errorMessage) : that.errorMessage != null) return false;
        if (loanObtained != null ? !loanObtained.equals(that.loanObtained) : that.loanObtained != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = loanObtained != null ? loanObtained.hashCode() : 0;
        result = 31 * result + (errorMessage != null ? errorMessage.hashCode() : 0);
        return result;
    }
}

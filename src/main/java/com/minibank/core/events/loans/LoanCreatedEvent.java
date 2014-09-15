package com.minibank.core.events.loans;

import com.minibank.core.events.CreatedEvent;

/**
 * Created by Ann on 06/09/14.
 */
public class LoanCreatedEvent extends CreatedEvent
{
    private final Boolean loanObtained;
    private final String message;

    public LoanCreatedEvent(Boolean loanObtained, String errorMessage)
    {
        this.loanObtained = loanObtained;
        this.message = errorMessage;
    }

    public Boolean isLoanObtained()
    {
        return loanObtained;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanCreatedEvent that = (LoanCreatedEvent) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;
        if (loanObtained != null ? !loanObtained.equals(that.loanObtained) : that.loanObtained != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        int result = loanObtained != null ? loanObtained.hashCode() : 0;
        result = 31 * result + (message != null ? message.hashCode() : 0);
        return result;
    }
}

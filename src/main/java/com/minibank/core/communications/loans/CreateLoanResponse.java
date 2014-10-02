package com.minibank.core.communications.loans;

import com.minibank.core.communications.CreateEntityResponse;
import com.minibank.core.communications.DomainResponse;

/**
 * Created by Ann on 06/09/14.
 */
public class CreateLoanResponse extends CreateEntityResponse
        implements DomainResponse
{
    private final Boolean loanObtained;
    private final String message;

    public CreateLoanResponse(Boolean loanObtained, String errorMessage)
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

        CreateLoanResponse that = (CreateLoanResponse) o;

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

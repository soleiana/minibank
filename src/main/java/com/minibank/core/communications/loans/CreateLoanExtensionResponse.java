package com.minibank.core.communications.loans;

import com.minibank.core.communications.CreateEntityResponse;
import com.minibank.core.communications.DomainResponse;

/**
 * Created by Ann on 06/09/14.
 */
public class CreateLoanExtensionResponse extends CreateEntityResponse
    implements DomainResponse
{
    private final String message;

    public CreateLoanExtensionResponse(String message)
    {
        this.message = message;
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

        CreateLoanExtensionResponse that = (CreateLoanExtensionResponse) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return message != null ? message.hashCode() : 0;
    }
}

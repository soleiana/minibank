package com.minibank.core.communications.loans;

import com.minibank.core.communications.CreateEntityResponse;
import com.minibank.core.communications.DomainResponse;

/**
 * Created by Ann on 06/09/14.
 */
public class CreateLoanResponse extends CreateEntityResponse
        implements DomainResponse
{
    private final String message;

    public CreateLoanResponse(Boolean loanCreated, String message)
    {
        super.created = loanCreated;
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

}

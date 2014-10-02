package com.minibank.core.communications.loans;

import com.minibank.core.communications.CreateEntityQuery;
import com.minibank.core.communications.DomainQuery;

/**
 * Created by Ann on 06/09/14.
 */
public class CreateLoanExtensionQuery extends CreateEntityQuery
    implements DomainQuery<CreateLoanExtensionResponse>
{
    private final Integer loanId;

    public CreateLoanExtensionQuery(Integer loanId)
    {
        this.loanId = loanId;
    }

    public Integer getLoanId()
    {
        return loanId;
    }
}

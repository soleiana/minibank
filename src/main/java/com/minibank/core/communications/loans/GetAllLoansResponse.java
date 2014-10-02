package com.minibank.core.communications.loans;

import com.minibank.core.communications.DomainResponse;
import com.minibank.core.communications.GetEntityResponse;
import com.minibank.core.communications.loans.domain.AllLoansDetails;

/**
 * Created by Ann on 06/09/14.
 */
public class GetAllLoansResponse extends GetEntityResponse
    implements DomainResponse
{
    private final AllLoansDetails allLoansDetails;

    public GetAllLoansResponse(AllLoansDetails allLoansDetails, Boolean entityFound)
    {
        this.allLoansDetails = allLoansDetails;
        super.entityFound = entityFound;
    }

    public AllLoansDetails getAllLoansDetails()
    {
        return allLoansDetails;
    }
}

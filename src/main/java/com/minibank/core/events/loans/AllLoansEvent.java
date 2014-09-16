package com.minibank.core.events.loans;

import com.minibank.core.events.ReadEvent;
import com.minibank.core.events.loans.domain.AllLoansDetails;

/**
 * Created by Ann on 06/09/14.
 */
public class AllLoansEvent extends ReadEvent
{
    private final AllLoansDetails allLoansDetails;

    public AllLoansEvent(AllLoansDetails allLoansDetails, Boolean entityFound)
    {
        this.allLoansDetails = allLoansDetails;
        super.entityFound = entityFound;
    }

    public AllLoansDetails getAllLoansDetails()
    {
        return allLoansDetails;
    }
}

package com.minibank.rest.factories;

import com.minibank.core.events.loans.domain.AllLoansDetails;
import com.minibank.rest.domain.AllLoans;

/**
 * Created by Ann on 14/09/14.
 */
public interface AllLoansFactory
{
    AllLoans getNewAllLoans(AllLoansDetails allLoansDetails);
}

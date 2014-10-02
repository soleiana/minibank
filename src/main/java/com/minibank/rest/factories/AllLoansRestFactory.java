package com.minibank.rest.factories;

import com.minibank.core.communications.loans.domain.AllLoansDetails;
import com.minibank.rest.domain.AllLoans;

/**
 * Created by Ann on 14/09/14.
 */
public interface AllLoansRestFactory
{
    AllLoans getNewAllLoans(AllLoansDetails allLoansDetails);
}

package com.minibank.core.communications.loans.factories;

import com.minibank.core.domain.AllLoans;
import com.minibank.core.communications.loans.domain.AllLoansDetails;


/**
 * Created by Ann on 14/09/14.
 */
public interface AllLoansDetailsFactory
{
    AllLoansDetails getNewAllLoansDetails(AllLoans allLoans);
}

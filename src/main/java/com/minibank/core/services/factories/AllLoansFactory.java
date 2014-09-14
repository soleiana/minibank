package com.minibank.core.services.factories;

import com.minibank.core.domain.AllLoans;
import com.minibank.core.events.loans.domain.RequestAllLoansDetails;
import com.minibank.core.repository.DBException;

/**
 * Created by Ann on 14/09/14.
 */
public interface AllLoansFactory
{
    AllLoans getNewAllLoans(RequestAllLoansDetails requestAllLoansDetails)
                   throws DBException;
}

package com.minibank.core.services.factories;

import com.minibank.core.domain.AllLoans;
import com.minibank.core.repositories.DBException;

/**
 * Created by Ann on 14/09/14.
 */
public interface AllLoansFactory
{
    AllLoans getNewAllLoans(Integer customerId)
                   throws DBException;
}

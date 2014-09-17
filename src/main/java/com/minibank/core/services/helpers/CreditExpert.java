package com.minibank.core.services.helpers;

import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repositories.DBException;

/**
 * Created by Ann on 12/09/14.
 */
public interface CreditExpert
{
    boolean hasRisks(LoanRequest loanRequest) throws DBException;
}

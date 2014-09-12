package com.minibank.core.services;

import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repository.DBException;

/**
 * Created by Ann on 12/09/14.
 */
public interface CreditExpert
{
    boolean hasRisks(LoanRequest loanRequest) throws DBException;
}

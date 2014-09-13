package com.minibank.core.services.factories;

import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repository.DBException;

/**
 * Created by Ann on 12/09/14.
 */
public interface LoanFactory
{
    Loan getNewLoan(LoanRequest loanRequest)throws DBException;
}

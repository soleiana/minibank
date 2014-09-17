package com.minibank.core.services.factories;

import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repositories.DBException;

/**
 * Created by Ann on 12/09/14.
 */
public interface LoanFactory
{
    Loan getNewLoan(LoanRequest loanRequest)throws DBException;

    Loan getExtendedLoan(LoanExtension loanExtension) throws DBException;
}

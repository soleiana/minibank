package com.minibank.core.services.helpers;

import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repositories.DBException;

/**
 * Created by Ann on 12/09/14.
 */
public interface DBWriter
{
    void create(LoanRequest loanRequest) throws DBException;

    void update(LoanRequest loanRequest) throws DBException;

    void update(Loan extendedLoan) throws DBException;

    void create(Loan loan) throws  DBException;

    void create(LoanExtension loanExtension) throws DBException;
}

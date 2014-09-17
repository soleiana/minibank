package com.minibank.core.repositories;

import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;

import java.util.List;

/**
 * Created by Ann on 06/09/14.
 */
public interface LoanExtensionRepository
{
    void create(LoanExtension loanExtension) throws DBException;

    List<LoanExtension> getByLoan(Loan loan) throws DBException;

    LoanExtension getLast() throws  DBException;
}

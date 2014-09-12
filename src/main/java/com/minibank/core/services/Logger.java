package com.minibank.core.services;

import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.domain.RequestIP;
import com.minibank.core.repository.DBException;

/**
 * Created by Ann on 12/09/14.
 */
public interface Logger
{
    void log(LoanRequest loanRequest) throws DBException;

    void update(LoanRequest loanRequest) throws DBException;

    void log(RequestIP requestIP) throws DBException;

    void log(Loan loan) throws  DBException;

    void log(LoanExtension loanExtension) throws DBException;
}

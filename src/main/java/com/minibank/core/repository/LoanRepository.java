package com.minibank.core.repository;

import com.minibank.core.domain.Loan;

/**
 * Created by Ann on 06/09/14.
 */
public interface LoanRepository
{
    void create(Loan loan) throws  DBException;

    void update(Loan loan) throws DBException;

    Loan getById(Integer id) throws DBException;
}

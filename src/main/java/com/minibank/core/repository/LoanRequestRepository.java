package com.minibank.core.repository;

import com.minibank.core.domain.LoanRequest;

/**
 * Created by Ann on 06/09/14.
 */
public interface LoanRequestRepository
{
    void create(LoanRequest loanRequest)  throws DBException;

    void update(LoanRequest loanRequest) throws DBException;

    LoanRequest getById(Integer id) throws  DBException;
}

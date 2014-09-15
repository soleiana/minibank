package com.minibank.core.repository;

import com.minibank.core.domain.LoanRequest;
import com.minibank.core.domain.RequestIP;

import java.util.List;

/**
 * Created by Ann on 06/09/14.
 */
public interface LoanRequestRepository
{
    void create(LoanRequest loanRequest) throws DBException;

    void update(LoanRequest loanRequest) throws DBException;

    LoanRequest getById(Integer id) throws DBException;

    List<LoanRequest> getByRequestIP(RequestIP requestIP) throws DBException;

    LoanRequest getLast() throws DBException;
}

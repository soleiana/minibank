package com.minibank.core.repositories;

import com.minibank.core.domain.LoanRequest;

import java.util.List;

/**
 * Created by Ann on 06/09/14.
 */
public interface LoanRequestRepository
{
    void create(LoanRequest loanRequest);

    void update(LoanRequest loanRequest);

    LoanRequest getById(Integer id);

    List<LoanRequest> getByRequestIp(String requestIp);

    LoanRequest getLast();
}

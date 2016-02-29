package com.minibank.testutil.services;

import com.minibank.core.model.Loan;
import com.minibank.core.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoanIdProvider {

    @Autowired
    private LoanRepository loanRepository;

    @Transactional
    public Integer getLastLoanId() {
        Loan loan = loanRepository.getLast();
        return loan.getId();
    }

}

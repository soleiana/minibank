package com.minibank.testutil.services;

import com.minibank.core.model.Loan;
import com.minibank.testutil.repositories.TestLoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LoanIdProvider {

    @Autowired
    private TestLoanRepository testLoanRepository;

    @Transactional
    public Integer getLastLoanId() {
        Loan loan = testLoanRepository.getLast();
        return loan.getId();
    }

}

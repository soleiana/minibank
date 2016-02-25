package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.model.*;
import com.minibank.core.repositories.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static junit.framework.TestCase.assertNotNull;


public class LoanExtensionRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private LoanExtensionRepository loanExtensionRepository;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanRepository loanRepository;

    private LoanExtension loanExtension;
    private Customer customer;
    private LoanRequest loanRequest;
    private Loan loan;

    @Before
    public void setUp() {
        dbCleaner.clear();
        customer = CustomerFixture.standardCustomer();
        testCustomerRepository.create(customer);
        createLoanExtension();
    }

    @Test
    public void testCreate() {
        loanExtensionRepository.create(loanExtension);
        assertNotNull(loanExtension.getId());
    }

    private void createLoanRequest() {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setCustomer(customer);
        loanRequestRepository.create(loanRequest);
    }

    private void createLoan() {
        createLoanRequest();
        loan = LoanFixture.standardLoan();
        loanRepository.create(loan);
    }

    private void createLoanExtension() {
        createLoan();
        loanExtension = LoanExtensionFixture.standardLoanExtension();
        loan.addLoanExtension(loanExtension);
    }

}

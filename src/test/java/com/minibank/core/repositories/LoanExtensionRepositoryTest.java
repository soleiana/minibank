package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.repositories.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertNotNull;



public class LoanExtensionRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private LoanExtensionRepository loanExtensionRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanRepository loanRepository;

    private LoanExtension loanExtension;
    private Customer customer;
    private LoanRequest loanRequest;
    private Loan loan;

    @Before
    @Transactional
    public void setUp() {
        dbCleaner.clear();
        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);
        createLoanExtension();
    }

    @Test
    @Transactional
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
        loan.setLoanRequest(loanRequest);
        loanRepository.create(loan);
    }

    private void createLoanExtension() {
        createLoan();
        loanExtension = LoanExtensionFixture.standardLoanExtension();
        loan.getLoanExtensions().add(loanExtension);
    }

}

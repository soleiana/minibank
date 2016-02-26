package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.fixtures.LoanExtensionFixture;
import com.minibank.core.fixtures.LoanFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.Customer;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanExtension;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.helpers.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestLoanExtensionRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private TestLoanExtensionRepository testLoanExtensionRepository;

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
    }

    @Test
    public void testGetLastIfLoanExtensionDoesNotExist() {
        LoanExtension retrievedLoanExtension = testLoanExtensionRepository.getLast();
        assertNull(retrievedLoanExtension);
    }

    @Test
    public void testGetLastIfLoanExtensionExists() {
        createLoanExtension();
        LoanExtension retrievedLoanExtension = testLoanExtensionRepository.getLast();
        assertEquals(loanExtension, retrievedLoanExtension);
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
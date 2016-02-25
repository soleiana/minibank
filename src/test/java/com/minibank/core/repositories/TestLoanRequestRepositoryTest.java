package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.model.Customer;
import com.minibank.core.model.CustomerFixture;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.model.LoanRequestFixture;
import com.minibank.core.repositories.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestLoanRequestRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private TestLoanRequestRepository testLoanRequestRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    private LoanRequest loanRequest;
    private Customer customer;

    @Before
    public void setUp() {
        dbCleaner.clear();
        customer = CustomerFixture.standardCustomer();
        testCustomerRepository.create(customer);
    }

    @Test
    public void testGetLastIfLoanRequestDoesNotExist() {
        LoanRequest retrievedLoanRequest = testLoanRequestRepository.getLast();
        assertNull(retrievedLoanRequest);
    }

    @Test
    public void testGetLastIfLoanRequestExists() {
        createLoanRequest();
        LoanRequest retrievedLoanRequest = testLoanRequestRepository.getLast();
        assertEquals(loanRequest, retrievedLoanRequest);
    }

    private void createLoanRequest() {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setCustomer(customer);
        loanRequestRepository.create(loanRequest);
    }
}
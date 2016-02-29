package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.Customer;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.helpers.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestLoanRequestRepositoryTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private TestLoanRequestRepository testLoanRequestRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @Before
    public void setUp() {
        databaseCleaner.clear();
        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);
    }

    @Test
    public void testGetLastIfLoanRequestDoesNotExist() {
        LoanRequest retrievedLoanRequest = testLoanRequestRepository.getLast();
        assertNull(retrievedLoanRequest);
    }

    @Test
    public void testGetLastIfLoanRequestExists() {
        LoanRequest loanRequest = createLoanRequest();
        LoanRequest retrievedLoanRequest = testLoanRequestRepository.getLast();
        assertEquals(loanRequest, retrievedLoanRequest);
    }

    private LoanRequest createLoanRequest() {
        LoanRequest loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setCustomer(customer);
        loanRequestRepository.create(loanRequest);
        return loanRequest;
    }
}
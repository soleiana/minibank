package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.fixtures.LoanFixture;
import com.minibank.core.model.Customer;
import com.minibank.core.model.Loan;
import com.minibank.core.repositories.helpers.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestLoanRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private TestLoanRepository testLoanRepository;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    private Customer customer;
    private Loan loan;

    @Before
    public void setUp() {
        dbCleaner.clear();
        customer = CustomerFixture.standardCustomer();
        testCustomerRepository.create(customer);
        loan = LoanFixture.standardLoan();
    }

    @Test
    public void testGetLastIfLoanDoesNotExist() {
        Loan retrievedLoan = testLoanRepository.getLast();
        assertNull(retrievedLoan);
    }

    @Test
    public void testGetLastIfLoanExists() {
        loanRepository.create(loan);
        Loan retrievedLoan = testLoanRepository.getLast();
        assertEquals(loan, retrievedLoan);
    }

}
package com.minibank.testutil.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.fixtures.LoanFixture;
import com.minibank.core.model.Customer;
import com.minibank.core.model.Loan;
import com.minibank.core.repositories.CustomerRepository;
import com.minibank.core.repositories.LoanRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestLoanRepositoryTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private TestLoanRepository testLoanRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;
    private Loan loan;

    @Before
    public void setUp() {
        databaseCleaner.clear();
        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);
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
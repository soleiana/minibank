package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.fixtures.LoanFixture;
import com.minibank.core.model.Customer;
import com.minibank.core.model.Loan;
import com.minibank.testutil.repositories.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class LoanRepositoryTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRepository loanRepository;

    private Customer customer;

    @Before
    public void setUp() {
        databaseCleaner.clear();

        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);
    }

    @Test
    public void testCreate() {
        Loan loan =  LoanFixture.standardLoan();
        loanRepository.create(loan);
        assertNotNull(loan.getId());
    }

    @Test
    public void testUpdate() {
        Loan loan =  LoanFixture.standardLoan();
        loanRepository.create(loan);
        loan.setCurrentInterestRate(LoanFixture.NEW_CURRENT_INTEREST_RATE);
        loan.setCurrentInterest(LoanFixture.NEW_CURRENT_INTEREST);
        loan.setEndDate(LoanFixture.NEW_END_DATE);

        loanRepository.update(loan);

        assertEquals(LoanFixture.NEW_CURRENT_INTEREST_RATE, loan.getCurrentInterestRate());
        assertEquals(LoanFixture.NEW_CURRENT_INTEREST, loan.getCurrentInterest());
        assertEquals(LoanFixture.NEW_END_DATE, loan.getEndDate());
    }

    @Test
    public void testGetById() {
        Loan loan =  LoanFixture.standardLoan();
        loanRepository.create(loan);
        Loan retrievedLoan = loanRepository.getById(loan.getId());
        assertEquals(loan, retrievedLoan);
    }

}

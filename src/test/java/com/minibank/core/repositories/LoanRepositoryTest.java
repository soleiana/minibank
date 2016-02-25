package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.model.*;
import com.minibank.core.repositories.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;


public class LoanRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    @Autowired
    private LoanRepository loanRepository;

    private Customer customer;
    private Loan loan;


    @Before
    @Transactional
    public void setUp() {
        dbCleaner.clear();

        customer = CustomerFixture.standardCustomer();
        testCustomerRepository.create(customer);
        createLoan();
    }

    @Test
    @Transactional
    public void testCreate() {
        loanRepository.create(loan);
        assertNotNull(loan.getId());
    }

    @Test
    @Transactional
    public void testUpdate() {
        loanRepository.create(loan);
        loan.setCurrInterestRate(LoanFixture.NEW_CURRENT_INTEREST_RATE);
        loan.setCurrInterest(LoanFixture.NEW_CURRENT_INTEREST);
        loan.setEndDate(LoanFixture.NEW_END_DATE);

        loanRepository.update(loan);

        assertEquals(LoanFixture.NEW_CURRENT_INTEREST_RATE, loan.getCurrInterestRate());
        assertEquals(LoanFixture.NEW_CURRENT_INTEREST, loan.getCurrInterest());
        assertEquals(LoanFixture.NEW_END_DATE, loan.getEndDate());
    }

    @Test
    @Transactional
    public void testGetById() {
        loanRepository.create(loan);
        Loan ln = loanRepository.getById(loan.getId());
        assertEquals(loan,ln);
    }

    private void createLoan() {
        loan = LoanFixture.standardLoan();
    }

}

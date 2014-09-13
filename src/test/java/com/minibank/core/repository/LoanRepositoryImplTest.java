package com.minibank.core.repository;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repository.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Ann on 10/09/14.
 */
public class LoanRepositoryImplTest extends SpringContextTest
{
    @Autowired
    private DBCleaner dbCleaner;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RequestIPRepository requestIPRepository;
    @Autowired
    private LoanRequestRepository loanRequestRepository;
    @Autowired
    private LoanRepository loanRepository;

    private Customer customer;
    private RequestIP requestIP;
    private LoanRequest loanRequest;
    private Loan loan;

    @Before
    @Transactional
    public void setUp() throws DBException
    {
        dbCleaner.clear();

        customer = CustomerFixture.standardCustomer();
        requestIP = RequestIPFixture.standardRequestIP();
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loan = LoanFixture.standardLoan();

        customerRepository.create(customer);
        requestIPRepository.create(requestIP);
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIP(requestIP);

        loanRequestRepository.create(loanRequest);

        loan.setCustomer(customer);
        loan.setLoanRequest(loanRequest);

    }

    @Test
    @Transactional
    public void testCreate() throws DBException
    {
        loanRepository.create(loan);
        assertNotNull(loan.getId());
    }

    @Test
    @Transactional
    public void testUpdate() throws DBException
    {
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
    public void testGetById() throws DBException
    {
        loanRepository.create(loan);
        Loan ln = loanRepository.getById(loan.getId());
        assertEquals(loan,ln);

    }
}

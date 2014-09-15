package com.minibank.core.repository;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.repository.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Ann on 10/09/14.
 */
public class LoanExtensionRepositoryImplTest  extends SpringContextTest
{
    @Autowired
    private DBCleaner dbCleaner;
    @Autowired
    private LoanExtensionRepository loanExtensionRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RequestIPRepository requestIPRepository;
    @Autowired
    private LoanRequestRepository loanRequestRepository;
    @Autowired
    private LoanRepository loanRepository;

    private LoanExtension loanExtension;
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
        customerRepository.create(customer);
        requestIPRepository.create(requestIP);

        createLoanExtension();
    }

    private void createLoanRequest() throws DBException
    {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIP(requestIP);
        loanRequestRepository.create(loanRequest);
    }

    private void createLoan() throws  DBException
    {
        createLoanRequest();
        loan = LoanFixture.standardLoan();
        loan.setCustomer(customer);
        loan.setLoanRequest(loanRequest);
        loanRepository.create(loan);
    }

    private void createLoanExtension() throws DBException
    {
        createLoan();
        loanExtension = LoanExtensionFixture.standardLoanExtension();
        loanExtension.setLoan(loan);
    }

    @Test
    @Transactional
    public void testCreate() throws DBException
    {
        loanExtensionRepository.create(loanExtension);
        assertNotNull(loanExtension.getId());
    }

    @Test
    @Transactional
    public void testGetByLoan() throws DBException
    {
        createLoan();
        LoanExtension loanExtension1 = LoanExtensionFixture.standardLoanExtension();
        loanExtension1.setLoan(loan);
        LoanExtension loanExtension2 = LoanExtensionFixture.standardLoanExtension();
        loanExtension2.setLoan(loan);
        loanExtensionRepository.create(loanExtension1);
        loanExtensionRepository.create(loanExtension2);

        List<LoanExtension> loanExtensions = loanExtensionRepository.getByLoan(loan);
        assertEquals(2, loanExtensions.size());
    }

}

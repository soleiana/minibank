package com.minibank.core.repository;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.repository.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

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

        loanExtension = LoanExtensionFixture.standardLoanExtension();
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
        loanRepository.create(loan);

        loanExtension.setLoan(loan);
    }

    @Test
    @Transactional
    public void testCreate() throws DBException
    {
        loanExtensionRepository.create(loanExtension);
        assertNotNull(loanExtension.getId());
    }
}

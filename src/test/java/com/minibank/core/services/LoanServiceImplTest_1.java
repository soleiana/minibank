package com.minibank.core.services;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.events.domain.LoanRequestDetailsFixture;
import com.minibank.core.events.loans.*;
import com.minibank.core.events.loans.domain.LoanRequestDetails;
import com.minibank.core.repository.*;
import com.minibank.core.repository.tools.DBCleaner;
import com.minibank.core.services.common.Message;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.*;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Ann on 14/09/14.
 */
public class LoanServiceImplTest_1 extends SpringContextTest
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
    @Autowired
    private BankParamsRepository bankParamsRepository;
    @Autowired
    private LoanExtensionRepository loanExtensionRepository;
    @Autowired
    private LoanService loanService;

    private BankParams bankParams;
    private Customer customer;
    private RequestIP requestIP;
    private LoanRequest loanRequest;
    private Loan loan;

    @Before
    @Transactional
    public void setUp() throws Exception
    {
        dbCleaner.clear();
        bankParams = BankParamsFixture.standardBankParams();
        bankParamsRepository.create(bankParams);

        //We assume that customer already exists in DB
        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);
        //requestIP = RequestIPFixture.standardRequestIP();
        //requestIPRepository.create(requestIP);

    }

    private CreateLoanEvent createCreateLoanEvent()throws DBException
    {
        LoanRequestDetails loanRequestDetails =
                  LoanRequestDetailsFixture.standardLoanRequestDetails();
        loanRequestDetails.setCustomerId(customer.getId());

        return new CreateLoanEvent(loanRequestDetails);
    }

    private CreateLoanExtensionEvent createCreateLoanExtensionEvent()throws DBException
    {
        //Precondition: customer already logged in and its record exists in database
        //Precondition: loan, subject to extension, exists in database

        loanRequest = LoanRequestFixture.standardLoanRequest();
        requestIP = RequestIPFixture.standardRequestIP();
        loan = LoanFixture.standardLoan();

        requestIPRepository.create(requestIP);
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIP(requestIP);
        loanRequestRepository.create(loanRequest);
        loan.setCustomer(customer);
        loan.setLoanRequest(loanRequest);
        loanRepository.create(loan);
        return new CreateLoanExtensionEvent(loan.getId());
    }


    @Test
    @Transactional
    public void testCreateLoan() throws Exception
    {
        CreateLoanEvent createLoanEvent = createCreateLoanEvent();
        LoanCreatedEvent expectedLoanCreatedEvent =
                new LoanCreatedEvent(true,Message.LOAN_OBTAINED_MESSAGE);

        LoanRequest loanRequest1 = loanRequestRepository.getLast();
        Loan loan1 = loanRepository.getLast();
        assertNull(loanRequest1);
        assertNull(loan1);

        LoanCreatedEvent loanCreatedEvent = loanService.createLoan(createLoanEvent);

        assertEquals(expectedLoanCreatedEvent, loanCreatedEvent);

        loanRequest1 = loanRequestRepository.getLast();
        loan1 = loanRepository.getLast();

        assertNotNull(loanRequest1);
        assertNotNull(loan1);
        assertEquals(customer.getId(), loan1.getCustomer().getId());
        assertEquals(customer.getId(), loanRequest1.getCustomer().getId());
    }

    @Test
    @Transactional
    public void testCreateLoanExtension() throws Exception
    {
       CreateLoanExtensionEvent createLoanExtensionEvent = createCreateLoanExtensionEvent();
       LoanExtensionCreatedEvent expectedEvent =
                new LoanExtensionCreatedEvent(Message.LOAN_EXTENSION_MESSAGE);

        LoanExtension loanExtension = loanExtensionRepository.getLast();
        assertNull(loanExtension);

       LoanExtensionCreatedEvent loanExtensionCreatedEvent =
               loanService.createLoanExtension(createLoanExtensionEvent);
        assertEquals(expectedEvent, loanExtensionCreatedEvent);

        loanExtension = loanExtensionRepository.getLast();
        assertNotNull(loanExtension);
        assertEquals(loan.getId(), loanExtension.getLoan().getId());
    }
}


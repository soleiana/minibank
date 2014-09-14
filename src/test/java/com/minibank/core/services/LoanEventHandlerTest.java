package com.minibank.core.services;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.events.LoanRequestDetailsFixture;
import com.minibank.core.events.loans.*;
import com.minibank.core.events.loans.domain.LoanExtensionDetails;
import com.minibank.core.events.loans.domain.LoanRequestDetails;
import com.minibank.core.repository.*;
import com.minibank.core.repository.tools.DBCleaner;
import com.minibank.core.services.common.Message;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Ann on 14/09/14.
 */
public class LoanEventHandlerTest extends SpringContextTest
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
    private LoanService loanService;

    private BankParams bankParams;
    private Customer customer;
    private RequestIP requestIP;
    private LoanRequest loanRequest;
    private Loan loan;

    @Before
    //@Transactional
    public void setUp() throws Exception
    {
        dbCleaner.clear();
        bankParams = BankParamsFixture.standardBankParams();
        bankParamsRepository.create(bankParams);

        //We assume that customer already exists in DB
        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);

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

        LoanExtensionDetails loanExtensionDetails = new LoanExtensionDetails();
        loanExtensionDetails.setLoanId(loan.getId());

        return new CreateLoanExtensionEvent(loanExtensionDetails);
    }

    @Test
    @Transactional
    public void testCreateLoan() throws Exception
    {
        CreateLoanEvent createLoanEvent = createCreateLoanEvent();
        LoanCreatedEvent expectedLoanCreatedEvent = new LoanCreatedEvent(true,null);
        LoanCreatedEvent loanCreatedEvent = loanService.createLoan(createLoanEvent);
        assertEquals(expectedLoanCreatedEvent, loanCreatedEvent);
    }

    @Test
    @Transactional
    public void testCreateLoanExtension() throws Exception
    {
       CreateLoanExtensionEvent createLoanExtensionEvent = createCreateLoanExtensionEvent();
       LoanExtensionCreatedEvent expectedEvent =
                new LoanExtensionCreatedEvent(Message.LOAN_EXTENSION_MESSAGE);

       LoanExtensionCreatedEvent loanExtensionCreatedEvent =
               loanService.createLoanExtension(createLoanExtensionEvent);
        assertEquals(expectedEvent, loanExtensionCreatedEvent);
    }

   /* @Test
    @Transactional
    public void testRequestAllLoans() throws Exception
    {

    }*/
}

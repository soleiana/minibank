package com.minibank.core.services;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.events.LoanRequestDetailsFixture;
import com.minibank.core.events.loans.CreateLoanEvent;
import com.minibank.core.events.loans.LoanCreatedEvent;
import com.minibank.core.events.loans.LoanRequestDetails;
import com.minibank.core.repository.BankParamsRepository;
import com.minibank.core.repository.CustomerRepository;
import com.minibank.core.repository.DBException;
import com.minibank.core.repository.RequestIPRepository;
import com.minibank.core.repository.tools.DBCleaner;
import com.minibank.core.services.common.DateTimeUtility;
import com.minibank.core.services.common.Message;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Time;

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
    private BankParamsRepository bankParamsRepository;
    @Autowired
    private LoanService loanService;

    private BankParams bankParams;
    private Customer customer;
    private RequestIP requestIP;
    private CreateLoanEvent createLoanEvent;


    @Before
    //@Transactional
    public void setUp() throws Exception
    {
        dbCleaner.clear();
        bankParams = BankParamsFixture.standardBankParams();
        bankParamsRepository.create(bankParams);

    }

    private CreateLoanEvent createCreateLoanEvent()throws DBException
    {
        //We assume that customer already exists in DB
        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);

        LoanRequestDetails loanRequestDetails =
                  LoanRequestDetailsFixture.standardLoanRequestDetails();
        loanRequestDetails.setCustomerId(customer.getId());

        CreateLoanEvent createLoanEvent = new CreateLoanEvent();
        createLoanEvent.setLoanRequestDetails(loanRequestDetails);
        return createLoanEvent;

    }

    @Test
    @Transactional
    public void testCreateLoan() throws Exception
    {
        createLoanEvent = createCreateLoanEvent();
        LoanCreatedEvent expectedLoanCreatedEvent = new LoanCreatedEvent(true,null);
        LoanCreatedEvent loanCreatedEvent = loanService.createLoan(createLoanEvent);
        assertEquals(expectedLoanCreatedEvent, loanCreatedEvent);
    }

 /*   @Test
    @Transactional
    public void testCreateLoanExtension() throws Exception
    {

    }

    @Test
    @Transactional
    public void testRequestAllLoans() throws Exception
    {

    }*/
}

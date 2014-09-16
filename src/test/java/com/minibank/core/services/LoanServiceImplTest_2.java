package com.minibank.core.services;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.events.loans.AllLoansEvent;
import com.minibank.core.events.loans.RequestAllLoansEvent;
import com.minibank.core.events.loans.domain.AllLoansDetails;
import com.minibank.core.repository.*;
import com.minibank.core.repository.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Ann on 15/09/14.
 */
public class LoanServiceImplTest_2 extends SpringContextTest
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

    private RequestAllLoansEvent createRequestAllLoansEvent_1() throws DBException
    {
        //Precondition: customer already logged in and its record exists in database
        //Precondition: customer has no loans
        return new RequestAllLoansEvent(customer.getId());
    }

    private RequestAllLoansEvent createRequestAllLoansEvent_2() throws DBException
    {
        //Precondition: customer already logged in and its record exists in database
        //Precondition: customer has 2 loans
        //Precondition: customer has no loan extensions

        return new RequestAllLoansEvent(customer.getId());
    }

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
        requestIP = RequestIPFixture.standardRequestIP();
        requestIPRepository.create(requestIP);
    }

    @Test
    @Transactional
    public void testRequestAllLoans_1() throws Exception
    {
        //Precondition: customer already logged in and its record exists in database
        //Precondition: customer has no loans
        RequestAllLoansEvent requestAllLoansEvent = createRequestAllLoansEvent_1();
        AllLoansDetails expectedAllLoansDetails = new AllLoansDetails();
        expectedAllLoansDetails.setName(customer.getName());
        expectedAllLoansDetails.setSurname(customer.getSurname());
        expectedAllLoansDetails.setCustomerId(customer.getId());

        AllLoansEvent allLoansEvent = loanService.requestAllLoans(requestAllLoansEvent);

        AllLoansDetails allLoansDetails = allLoansEvent.getAllLoansDetails();
        assertEquals(expectedAllLoansDetails.getCustomerId(),allLoansDetails.getCustomerId());
        assertEquals(expectedAllLoansDetails.getName(), allLoansDetails.getName());
        assertEquals(expectedAllLoansDetails.getSurname(), allLoansDetails.getSurname());
        assertEquals(0, allLoansDetails.getLoans().size());
        assertEquals(false, allLoansEvent.isEntityFound());
    }
}

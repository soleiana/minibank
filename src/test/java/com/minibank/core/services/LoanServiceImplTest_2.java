package com.minibank.core.services;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.events.loans.AllLoansEvent;
import com.minibank.core.events.loans.RequestAllLoansEvent;
import com.minibank.core.events.loans.domain.AllLoansDetails;
import com.minibank.core.repositories.*;
import com.minibank.core.repositories.tools.DBCleaner;
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
    private LoanExtension loanExtension;

    private void createLoan() throws DBException
    {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIP(requestIP);
        loanRequestRepository.create(loanRequest);
        loan = LoanFixture.standardLoan();
        loan.setCustomer(customer);
        loan.setLoanRequest(loanRequest);
        loanRepository.create(loan);
    }

    private void createLoanExtension() throws DBException
    {
        loanExtension = LoanExtensionFixture.standardLoanExtension();
        loanExtension.setLoan(loan);
        loanExtensionRepository.create(loanExtension);
    }

    private RequestAllLoansEvent createRequestAllLoansEvent() throws DBException
    {
        return new RequestAllLoansEvent(customer.getId());
    }

    private void prepareTestData_1() throws DBException
    {
        createLoan();
        createLoan();
    }

    private void prepareTestData_2() throws DBException
    {
        createLoan();
        createLoanExtension();
        createLoanExtension();
        createLoan();
    }

    @Before
    @Transactional
    public void setUp() throws Exception
    {
        dbCleaner.clear();

        bankParams = BankParamsFixture.standardBankParams();
        customer = CustomerFixture.standardCustomer();
        requestIP = RequestIPFixture.standardRequestIP();

        bankParamsRepository.create(bankParams);
        customerRepository.create(customer);
        requestIPRepository.create(requestIP);
    }

    @Test
    @Transactional
    public void testRequestAllLoans_1() throws Exception
    {
        //customer has no loans
        RequestAllLoansEvent requestAllLoansEvent = createRequestAllLoansEvent();

        AllLoansEvent allLoansEvent = loanService.requestAllLoans(requestAllLoansEvent);

        AllLoansDetails allLoansDetails = allLoansEvent.getAllLoansDetails();

        assertEquals(customer.getId(), allLoansDetails.getCustomerId());
        assertEquals(customer.getName(), allLoansDetails.getName());
        assertEquals(customer.getSurname(), allLoansDetails.getSurname());
        assertEquals(0, allLoansDetails.getLoans().size());
        assertEquals(false, allLoansEvent.isEntityFound());
    }

    @Test
    @Transactional
    public void testRequestAllLoans_2() throws Exception
    {
        //customer has 2 loans
        //customer has no loan extensions
        prepareTestData_1();
        RequestAllLoansEvent requestAllLoansEvent = createRequestAllLoansEvent();

        AllLoansEvent allLoansEvent = loanService.requestAllLoans(requestAllLoansEvent);

        AllLoansDetails allLoansDetails = allLoansEvent.getAllLoansDetails();

        assertEquals(2, allLoansDetails.getLoans().size());
        assertEquals(true, allLoansEvent.isEntityFound());
    }

    @Test
    @Transactional
    public void testRequestAllLoans_3() throws Exception
    {
        //customer has 2 loans
        //customer has 2 extensions of a loan
        prepareTestData_2();
        RequestAllLoansEvent requestAllLoansEvent = createRequestAllLoansEvent();

        AllLoansEvent allLoansEvent = loanService.requestAllLoans(requestAllLoansEvent);

        AllLoansDetails allLoansDetails = allLoansEvent.getAllLoansDetails();

        assertEquals(2, allLoansDetails.getLoans().size());
        assertEquals(2, allLoansDetails.getLoans().get(0).getLoanExtensions().size());
        assertEquals(0, allLoansDetails.getLoans().get(1).getLoanExtensions().size());
        assertEquals(true, allLoansEvent.isEntityFound());
    }
}

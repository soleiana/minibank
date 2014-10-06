package com.minibank.core.services.integration;

import com.minibank.SpringContextTest;
import com.minibank.core.communications.loans.CreateLoanExtensionQuery;
import com.minibank.core.communications.loans.CreateLoanExtensionResponse;
import com.minibank.core.domain.*;
import com.minibank.core.repositories.*;
import com.minibank.core.repositories.tools.DBCleaner;
import com.minibank.core.services.CreateLoanExtensionQueryHandler;
import com.minibank.core.services.common.Message;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

/**
 * Created by Ann on 02/10/14.
 */
public class CreateLoanExtensionQueryHandlerTest extends SpringContextTest
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
    private CreateLoanExtensionQueryHandler createLoanExtensionQueryHandler;

    private BankParams bankParams;
    private Customer customer;
    private RequestIP requestIP;
    private LoanRequest loanRequest;
    private Loan loan;

    private CreateLoanExtensionQuery createCreateLoanExtensionQuery()throws DBException
    {
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
        return new CreateLoanExtensionQuery(loan.getId());
    }

    @Before
    public void setUp() throws Exception
    {
        dbCleaner.clear();

        bankParams = BankParamsFixture.standardBankParams();
        bankParamsRepository.create(bankParams);
        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);
    }

    @Test
    @Transactional
    public void testExecute() throws Exception
    {
        CreateLoanExtensionQuery createLoanExtensionQuery = createCreateLoanExtensionQuery();
        CreateLoanExtensionResponse expectedResponse =
                new CreateLoanExtensionResponse(true, Message.LOAN_EXTENSION_OBTAINED_MESSAGE);

        LoanExtension loanExtension = loanExtensionRepository.getLast();
        assertNull(loanExtension);

        CreateLoanExtensionResponse createLoanExtensionResponse =
                createLoanExtensionQueryHandler.execute(createLoanExtensionQuery);

        assertEquals(expectedResponse.isCreated(), createLoanExtensionResponse.isCreated());
        assertEquals(expectedResponse.getMessage(), createLoanExtensionResponse.getMessage());

        loanExtension = loanExtensionRepository.getLast();
        assertNotNull(loanExtension);
        assertEquals(loan.getId(), loanExtension.getLoan().getId());

    }
}
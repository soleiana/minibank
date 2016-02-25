package com.minibank.core.services.integration;

import com.minibank.SpringContextTest;
import com.minibank.core.communications.CreateLoanExtensionQuery;
import com.minibank.core.communications.CreateLoanExtensionResponse;
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


public class CreateLoanExtensionQueryHandlerTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private BankParametersRepository bankParametersRepository;

    @Autowired
    private LoanExtensionRepository loanExtensionRepository;

    @Autowired
    private CreateLoanExtensionQueryHandler createLoanExtensionQueryHandler;

    private BankParameters bankParameters;
    private Customer customer;
    private LoanRequest loanRequest;
    private Loan loan;


    @Before
    public void setUp() {
        dbCleaner.clear();

        bankParameters = BankParametersFixture.standardBankParameters();
        bankParametersRepository.create(bankParameters);
        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);
    }

    @Test
    @Transactional
    public void testExecute() {
        CreateLoanExtensionQuery createLoanExtensionQuery = createCreateLoanExtensionQuery();
        CreateLoanExtensionResponse expectedResponse = new CreateLoanExtensionResponse(true, Message.LOAN_EXTENSION_OBTAINED_MESSAGE);

        LoanExtension loanExtension = loanExtensionRepository.getLast();
        assertNull(loanExtension);

        CreateLoanExtensionResponse createLoanExtensionResponse = createLoanExtensionQueryHandler.execute(createLoanExtensionQuery);

        assertEquals(expectedResponse.isCreated(), createLoanExtensionResponse.isCreated());
        assertEquals(expectedResponse.getMessage(), createLoanExtensionResponse.getMessage());

        loanExtension = loanExtensionRepository.getLast();
        assertNotNull(loanExtension);
        assertEquals(loanExtension.getInterest(), loan.getCurrInterest());
        assertEquals(loanExtension.getInterestRate(), loan.getCurrInterestRate());
    }

    private CreateLoanExtensionQuery createCreateLoanExtensionQuery() {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loan = LoanFixture.standardLoan();
        loanRequest.setCustomer(customer);
        loanRequestRepository.create(loanRequest);
        loan.setLoanRequest(loanRequest);
        loanRepository.create(loan);
        return new CreateLoanExtensionQuery(loan.getId());
    }
}

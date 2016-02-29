package com.minibank.core.services.integration;

import com.minibank.SpringContextTest;
import com.minibank.common.Messages;
import com.minibank.communications.CreateLoanQuery;
import com.minibank.communications.CreateLoanResponse;
import com.minibank.communications.fixtures.LoanRequestDetailsFixture;
import com.minibank.communications.model.LoanRequestDetails;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.Customer;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.services.CreateLoanQueryHandler;
import com.minibank.testutil.repositories.*;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


public class CreateLoanQueryHandlerTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    @Autowired
    private TestLoanRequestRepository testLoanRequestRepository;

    @Autowired
    private TestLoanRepository testLoanRepository;

    @Autowired
    private TestBankParametersRepository testBankParametersRepository;

    @Autowired
    private CreateLoanQueryHandler createLoanQueryHandler;

    private BankParameters bankParameters;
    private Customer customer;


    @Before
    public void setUp() {
        databaseCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        testBankParametersRepository.create(bankParameters);
        customer = CustomerFixture.standardCustomer();
        testCustomerRepository.create(customer);
    }

    @Test
    public void testExecute() {
        CreateLoanQuery createLoanQuery = createCreateLoanQuery();
        CreateLoanResponse expectedCreateLoanResponse = new CreateLoanResponse(true, Messages.LOAN_OBTAINED_MESSAGE);
        LoanRequest loanRequest = testLoanRequestRepository.getLast();
        Loan loan = testLoanRepository.getLast();
        assertNull(loanRequest);
        assertNull(loan);

        CreateLoanResponse createLoanResponse = createLoanQueryHandler.execute(createLoanQuery);

        assertEquals(expectedCreateLoanResponse.getMessage(), createLoanResponse.getMessage());
        assertEquals(expectedCreateLoanResponse.isCreated(), createLoanResponse.isCreated());

        loanRequest = testLoanRequestRepository.getLast();
        loan = testLoanRepository.getLast();

        assertNotNull(loanRequest);
        assertNotNull(loan);
        assertEquals(customer, loanRequest.getCustomer());
        assertEquals(1, customer.getLoans().size());
        assertTrue(customer.getLoans().contains(loan));
    }

    private CreateLoanQuery createCreateLoanQuery() {
        LoanRequestDetails loanRequestDetails = LoanRequestDetailsFixture.standardLoanRequestDetails();
        loanRequestDetails.setCustomerId(customer.getId());
        return new CreateLoanQuery(loanRequestDetails);
    }

}

package com.minibank.core.services.integration;

import com.minibank.SpringContextTest;
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
import com.minibank.core.repositories.BankParametersRepository;
import com.minibank.core.repositories.TestCustomerRepository;
import com.minibank.core.repositories.TestLoanRepository;
import com.minibank.core.repositories.TestLoanRequestRepository;
import com.minibank.core.repositories.helpers.DBCleaner;
import com.minibank.core.services.CreateLoanQueryHandler;
import com.minibank.core.services.common.Message;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.*;


public class CreateLoanQueryHandlerTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    @Autowired
    private TestLoanRequestRepository testLoanRequestRepository;

    @Autowired
    private TestLoanRepository testLoanRepository;

    @Autowired
    private BankParametersRepository bankParametersRepository;

    @Autowired
    private CreateLoanQueryHandler createLoanQueryHandler;

    private BankParameters bankParameters;
    private Customer customer;


    @Before
    public void setUp() {
        dbCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        bankParametersRepository.create(bankParameters);
        customer = CustomerFixture.standardCustomer();
        testCustomerRepository.create(customer);
    }

    @Test
    public void testExecute() {
        CreateLoanQuery createLoanQuery = createCreateLoanQuery();
        CreateLoanResponse expectedCreateLoanResponse = new CreateLoanResponse(true, Message.LOAN_OBTAINED_MESSAGE);
        LoanRequest loanRequest1 = testLoanRequestRepository.getLast();
        Loan loan1 = testLoanRepository.getLast();
        assertNull(loanRequest1);
        assertNull(loan1);

        CreateLoanResponse createLoanResponse = createLoanQueryHandler.execute(createLoanQuery);

        assertEquals(expectedCreateLoanResponse.getMessage(), createLoanResponse.getMessage());
        assertEquals(expectedCreateLoanResponse.isCreated(), createLoanResponse.isCreated());

        loanRequest1 = testLoanRequestRepository.getLast();
        loan1 = testLoanRepository.getLast();

        assertNotNull(loanRequest1);
        assertNotNull(loan1);
        assertEquals(customer, loanRequest1.getCustomer());
        assertEquals(1, customer.getLoans().size());
        assertTrue(customer.getLoans().contains(loan1));
    }

    private CreateLoanQuery createCreateLoanQuery() {
        LoanRequestDetails loanRequestDetails = LoanRequestDetailsFixture.standardLoanRequestDetails();
        loanRequestDetails.setCustomerId(customer.getId());
        return new CreateLoanQuery(loanRequestDetails);
    }

}

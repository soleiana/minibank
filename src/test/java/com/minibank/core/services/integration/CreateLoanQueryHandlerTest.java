package com.minibank.core.services.integration;

import com.minibank.SpringContextTest;
import com.minibank.core.communications.domain.LoanRequestDetailsFixture;
import com.minibank.core.communications.CreateLoanQuery;
import com.minibank.core.communications.CreateLoanResponse;
import com.minibank.core.communications.domain.LoanRequestDetails;
import com.minibank.core.domain.*;
import com.minibank.core.repositories.*;
import com.minibank.core.repositories.tools.DBCleaner;
import com.minibank.core.services.CreateLoanQueryHandler;
import com.minibank.core.services.common.Message;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;


public class CreateLoanQueryHandlerTest extends SpringContextTest {

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
    private CreateLoanQueryHandler createLoanQueryHandler;

    private BankParameters bankParameters;
    private Customer customer;


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
        CreateLoanQuery createLoanQuery = createCreateLoanQuery();
        CreateLoanResponse expectedCreateLoanResponse = new CreateLoanResponse(true, Message.LOAN_OBTAINED_MESSAGE);
        LoanRequest loanRequest1 = loanRequestRepository.getLast();
        Loan loan1 = loanRepository.getLast();
        assertNull(loanRequest1);
        assertNull(loan1);

        CreateLoanResponse createLoanResponse = createLoanQueryHandler.execute(createLoanQuery);

        assertEquals(expectedCreateLoanResponse.getMessage(), createLoanResponse.getMessage());
        assertEquals(expectedCreateLoanResponse.isCreated(), createLoanResponse.isCreated());

        loanRequest1 = loanRequestRepository.getLast();
        loan1 = loanRepository.getLast();

        assertNotNull(loanRequest1);
        assertNotNull(loan1);
        assertEquals(customer.getId(), loan1.getCustomer().getId());
        assertEquals(customer.getId(), loanRequest1.getCustomer().getId());
    }

    private CreateLoanQuery createCreateLoanQuery() {
        LoanRequestDetails loanRequestDetails = LoanRequestDetailsFixture.standardLoanRequestDetails();
        loanRequestDetails.setCustomerId(customer.getId());
        return new CreateLoanQuery(loanRequestDetails);
    }

}

package com.minibank.core.services.integration;

import com.minibank.SpringContextTest;
import com.minibank.common.Messages;
import com.minibank.communications.CreateLoanExtensionQuery;
import com.minibank.communications.CreateLoanExtensionResponse;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.fixtures.LoanFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanExtension;
import com.minibank.core.repositories.LoanRepository;
import com.minibank.core.services.CreateLoanExtensionQueryHandler;
import com.minibank.testutil.repositories.DatabaseCleaner;
import com.minibank.testutil.repositories.TestBankParametersRepository;
import com.minibank.testutil.repositories.TestLoanExtensionRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;


public class CreateLoanExtensionQueryHandlerTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private TestBankParametersRepository testBankParametersRepository;

    @Autowired
    private TestLoanExtensionRepository testLoanExtensionRepository;

    @Autowired
    private CreateLoanExtensionQueryHandler createLoanExtensionQueryHandler;

    private BankParameters bankParameters;
    private Loan loan;

    @Before
    public void setUp() {
        databaseCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        testBankParametersRepository.create(bankParameters);
    }

    @Test
    public void testExecute() {
        CreateLoanExtensionQuery createLoanExtensionQuery = createCreateLoanExtensionQuery();
        CreateLoanExtensionResponse expectedResponse = new CreateLoanExtensionResponse(true, Messages.LOAN_EXTENSION_OBTAINED_MESSAGE);

        LoanExtension loanExtension = testLoanExtensionRepository.getLast();
        assertNull(loanExtension);

        CreateLoanExtensionResponse createLoanExtensionResponse = createLoanExtensionQueryHandler.execute(createLoanExtensionQuery);

        assertEquals(expectedResponse.isCreated(), createLoanExtensionResponse.isCreated());
        assertEquals(expectedResponse.getMessage(), createLoanExtensionResponse.getMessage());

        loanExtension = testLoanExtensionRepository.getLast();
        assertNotNull(loanExtension);
        assertEquals(loan.getCurrentInterest(), loanExtension.getInterest());
        assertEquals(loan.getCurrentInterestRate(), loanExtension.getInterestRate());
        assertEquals(loan.getEndDate(), loanExtension.getEndDate());
    }

    private CreateLoanExtensionQuery createCreateLoanExtensionQuery() {
        loan = LoanFixture.standardLoan();
        loanRepository.create(loan);
        return new CreateLoanExtensionQuery(loan.getId());
    }
}

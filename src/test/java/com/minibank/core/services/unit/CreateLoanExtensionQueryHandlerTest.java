package com.minibank.core.services.unit;

import com.minibank.InjectMocksTest;
import com.minibank.core.communications.loans.CreateLoanExtensionQuery;
import com.minibank.core.communications.loans.CreateLoanExtensionResponse;
import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import com.minibank.core.domain.LoanExtensionFixture;
import com.minibank.core.domain.LoanFixture;
import com.minibank.core.repositories.LoanExtensionRepository;
import com.minibank.core.repositories.LoanRepository;
import com.minibank.core.services.CreateLoanExtensionQueryHandler;
import com.minibank.core.services.common.Message;
import com.minibank.core.services.factories.LoanExtensionFactory;
import com.minibank.core.services.factories.LoanFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;


public class CreateLoanExtensionQueryHandlerTest extends InjectMocksTest
{
    @InjectMocks
    private CreateLoanExtensionQueryHandler queryHandler;
    @Mock
    private LoanExtensionFactory loanExtensionFactory;
    @Mock
    private LoanFactory loanFactory;
    @Mock
    private LoanRepository loanRepository;
    @Mock
    private LoanExtensionRepository loanExtensionRepository;

    private LoanExtension loanExtension;
    private Loan loan;
    private Integer loanId;

    @Before
    public void setUp()
    {
        loan = LoanFixture.standardLoan();
        loanExtension = LoanExtensionFixture.standardLoanExtension();
        loanId = 1;

        when(loanExtensionFactory.getNewLoanExtension(loanId)).thenReturn(loanExtension);
        when(loanFactory.getExtendedLoan(loanExtension)).thenReturn(loan);
    }

    @Test
    public void testExecute()
    {
        //Positive path of execution
        //Customer obtains an extension of the loan

        CreateLoanExtensionResponse expectedResponse =
                new CreateLoanExtensionResponse(true, Message.LOAN_EXTENSION_OBTAINED_MESSAGE);
        CreateLoanExtensionQuery query = new CreateLoanExtensionQuery(loanId);

        CreateLoanExtensionResponse response = queryHandler.execute(query);

        assertNotNull(response);
        assertEquals(expectedResponse.isCreated(), response.isCreated());
        assertEquals(expectedResponse.getMessage(), response.getMessage());
        verify(loanExtensionFactory, times(1)).getNewLoanExtension(loanId);
        verify(loanExtensionRepository, times(1)).create(loanExtension);
        verify(loanFactory, times(1)).getExtendedLoan(loanExtension);
        verify(loanRepository, times(1)).update(loan);
    }
}

package com.minibank.core.services.unit;

import com.minibank.InjectMocksTest;
import com.minibank.common.Messages;
import com.minibank.communications.CreateLoanExtensionQuery;
import com.minibank.communications.CreateLoanExtensionResponse;
import com.minibank.core.factories.LoanExtensionFactory;
import com.minibank.core.factories.LoanFactory;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanExtension;
import com.minibank.core.repositories.LoanExtensionRepository;
import com.minibank.core.repositories.LoanRepository;
import com.minibank.core.services.CreateLoanExtensionQueryHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class CreateLoanExtensionQueryHandlerTest extends InjectMocksTest {

    private static final Integer loanId = 1;

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
    private Loan extendedLoan;


    @Before
    public void setUp() {
        loan = mock(Loan.class);
        extendedLoan = mock(Loan.class);
        loanExtension = mock(LoanExtension.class);

        when(loanRepository.getById(loanId)).thenReturn(loan);
        when(loanExtensionFactory.getLoanExtension(loan)).thenReturn(loanExtension);
        when(loanFactory.getExtendedLoan(loan, loanExtension)).thenReturn(extendedLoan);
    }

    @Test
    public void testExecute() {
        CreateLoanExtensionResponse expectedResponse = new CreateLoanExtensionResponse(true, Messages.LOAN_EXTENSION_OBTAINED_MESSAGE);
        CreateLoanExtensionQuery query = new CreateLoanExtensionQuery(loanId);

        CreateLoanExtensionResponse response = queryHandler.execute(query);

        assertNotNull(response);
        assertEquals(expectedResponse.isCreated(), response.isCreated());
        assertEquals(expectedResponse.getMessage(), response.getMessage());
        verify(loanExtensionFactory, times(1)).getLoanExtension(loan);
        verify(loanExtensionRepository, times(1)).create(loanExtension);
        verify(loanFactory, times(1)).getExtendedLoan(loan, loanExtension);
        verify(loanRepository, times(1)).update(extendedLoan);
    }
}

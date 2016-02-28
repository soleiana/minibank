package com.minibank.core.services.unit;

import com.minibank.InjectMocksTest;
import com.minibank.common.Messages;
import com.minibank.communications.CreateLoanExtensionQuery;
import com.minibank.communications.CreateLoanExtensionResponse;
import com.minibank.core.factories.LoanCoreFactory;
import com.minibank.core.factories.LoanExtensionCoreFactory;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanExtension;
import com.minibank.core.repositories.LoanRepository;
import com.minibank.core.services.CreateLoanExtensionQueryHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;


public class CreateLoanExtensionQueryHandlerTest extends InjectMocksTest {

    private static final Integer loanId = 1;

    @InjectMocks
    private CreateLoanExtensionQueryHandler queryHandler;

    @Mock
    private LoanExtensionCoreFactory loanExtensionCoreFactory;

    @Mock
    private LoanCoreFactory loanCoreFactory;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private LoanExtension loanExtension;

    @Mock
    private Loan loan;

    @Mock
    private Loan extendedLoan;


    @Before
    public void setUp() {
        when(loanRepository.getById(loanId)).thenReturn(loan);
        when(loanExtensionCoreFactory.getLoanExtension(loan)).thenReturn(loanExtension);
        when(loanCoreFactory.getExtendedLoan(loan, loanExtension)).thenReturn(extendedLoan);
    }

    @Test
    public void testExecute() {
        CreateLoanExtensionResponse expectedResponse = new CreateLoanExtensionResponse(true, Messages.LOAN_EXTENSION_OBTAINED_MESSAGE);
        CreateLoanExtensionQuery query = new CreateLoanExtensionQuery(loanId);

        CreateLoanExtensionResponse response = queryHandler.execute(query);

        TestUtility.assertResponse(expectedResponse, response);
        verify(loanExtensionCoreFactory, times(1)).getLoanExtension(loan);
        verify(extendedLoan, times(1)). addLoanExtension(loanExtension);
        verify(loanCoreFactory, times(1)).getExtendedLoan(loan, loanExtension);
        verify(loanRepository, times(1)).update(extendedLoan);
    }
}

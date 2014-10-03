package com.minibank.core.services.unit;

import com.minibank.InjectMocksTest;
import com.minibank.core.communications.loans.CreateLoanExtensionQuery;
import com.minibank.core.communications.loans.CreateLoanExtensionResponse;
import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import com.minibank.core.domain.LoanExtensionFixture;
import com.minibank.core.domain.LoanFixture;
import com.minibank.core.services.CreateLoanExtensionQueryHandler;
import com.minibank.core.services.common.Message;
import com.minibank.core.services.factories.LoanExtensionFactory;
import com.minibank.core.services.factories.LoanFactory;
import com.minibank.core.services.helpers.CreditExpert;
import com.minibank.core.services.helpers.DBWriter;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Ann on 02/10/14.
 */
public class CreateLoanExtensionQueryHandlerTest extends InjectMocksTest
{
    @InjectMocks
    private CreateLoanExtensionQueryHandler queryHandler;
    @Mock
    private DBWriter dbWriter;
    @Mock
    private LoanExtensionFactory loanExtensionFactory;
    @Mock
    private LoanFactory loanFactory;

    private LoanExtension loanExtension;
    private Loan loan;
    private Integer loanId;

    @Before
    public void setUp() throws Exception
    {
        loan = LoanFixture.standardLoan();
        loanExtension = LoanExtensionFixture.standardLoanExtension();
        loanId = 1;

        when(loanExtensionFactory.getNewLoanExtension(loanId)).thenReturn(loanExtension);
        when(loanFactory.getExtendedLoan(loanExtension)).thenReturn(loan);
    }

    @Test
    public void testExecute_1() throws Exception
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
        verify(dbWriter, times(1)).create(loanExtension);
        verify(loanFactory, times(1)).getExtendedLoan(loanExtension);
        verify(dbWriter, times(1)).update(loan);
    }

    @Test
    public void testExecute_2() throws Exception
    {
        //Negative path of execution
        //Customer does not obtain an extension of the loan because of the database failure

        doThrow(new RuntimeException()).when(dbWriter).create(loanExtension);

        CreateLoanExtensionResponse expectedResponse =
                new CreateLoanExtensionResponse(false, Message.LOAN_EXTENSION_ERROR_MESSAGE);
        CreateLoanExtensionQuery query = new CreateLoanExtensionQuery(loanId);

        CreateLoanExtensionResponse response = queryHandler.execute(query);

        assertNotNull(response);
        assertEquals(expectedResponse.isCreated(), response.isCreated());
        assertEquals(expectedResponse.getMessage(), response.getMessage());
        verify(loanExtensionFactory, times(1)).getNewLoanExtension(loanId);
        verify(dbWriter, times(1)).create(loanExtension);
        verify(loanFactory, times(0)).getExtendedLoan(loanExtension);
        verify(dbWriter, times(0)).update(loan);
    }
}

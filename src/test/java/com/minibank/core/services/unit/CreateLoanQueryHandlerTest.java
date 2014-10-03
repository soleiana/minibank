package com.minibank.core.services.unit;

import com.minibank.InjectMocksTest;
import com.minibank.core.communications.domain.LoanRequestDetailsFixture;
import com.minibank.core.communications.loans.CreateLoanQuery;
import com.minibank.core.communications.loans.CreateLoanResponse;
import com.minibank.core.communications.loans.domain.*;
import com.minibank.core.domain.*;
import com.minibank.core.domain.Loan;
import com.minibank.core.services.CreateLoanQueryHandler;
import com.minibank.core.services.common.Message;
import com.minibank.core.services.factories.LoanFactory;
import com.minibank.core.services.factories.LoanRequestFactory;
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
public class CreateLoanQueryHandlerTest extends InjectMocksTest
{
    @InjectMocks
    private CreateLoanQueryHandler queryHandler;
    @Mock
    private DBWriter dbWriter;
    @Mock
    private LoanRequestFactory loanRequestFactory;
    @Mock
    private LoanFactory loanFactory;
    @Mock
    private CreditExpert creditExpert;

    private LoanRequest loanRequest;
    private Loan loan;
    private LoanRequestDetails loanRequestDetails;

    @Before
    public void setUp() throws Exception
    {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loan = LoanFixture.standardLoan();
        loanRequestDetails = LoanRequestDetailsFixture.standardLoanRequestDetails();

        when(loanRequestFactory.getNewLoanRequest(any(LoanRequestDetails.class))).thenReturn(loanRequest);
        when(loanFactory.getNewLoan(any(LoanRequest.class))).thenReturn(loan);

    }

    @Test
    public void testExecute_1() throws Exception
    {
         //Positive path of execution
        //Customer obtains a loan

        when(creditExpert.hasRisks(any(LoanRequest.class))).thenReturn(false);

        CreateLoanResponse expectedResponse = new CreateLoanResponse(true, Message.LOAN_OBTAINED_MESSAGE);
        CreateLoanQuery query = new CreateLoanQuery(loanRequestDetails);

        CreateLoanResponse response = queryHandler.execute(query);
        assertNotNull(response);
        assertEquals(expectedResponse.getMessage(), response.getMessage());
        assertEquals(expectedResponse.isCreated(), response.isCreated());
        verify(loanRequestFactory, times(1)).getNewLoanRequest(loanRequestDetails);
        verify(dbWriter, times(1)).create(loanRequest);
        verify(creditExpert, times(1)).hasRisks(loanRequest);
        verify(loanFactory, times(1)).getNewLoan(loanRequest);
        verify(dbWriter, times(1)).create(loan);
    }

    @Test
    public void testExecute_2() throws Exception
    {
        //Negative path of execution
        //Customer is refused a loan because of the risks surrounding the loan request

        when(creditExpert.hasRisks(any(LoanRequest.class))).thenReturn(true);

        CreateLoanResponse expectedResponse = new CreateLoanResponse(false, Message.LOAN_ERROR_MESSAGE);
        CreateLoanQuery query =  new CreateLoanQuery(loanRequestDetails);

        CreateLoanResponse response = queryHandler.execute(query);

        assertNotNull(response);
        assertEquals(expectedResponse.getMessage(), response.getMessage());
        assertEquals(expectedResponse.isCreated(), response.isCreated());
        verify(loanRequestFactory, times(1)).getNewLoanRequest(loanRequestDetails);
        verify(dbWriter, times(1)).create(loanRequest);
        verify(creditExpert, times(1)).hasRisks(loanRequest);
        verify(loanFactory, times(0)).getNewLoan(loanRequest);
        verify(dbWriter, times(0)).create(loan);
    }

    @Test
    public void testExecute_3() throws Exception
    {
        //Negative path of execution
        //Customer is refused a loan because of the database failure

        when(creditExpert.hasRisks(any(LoanRequest.class))).thenReturn(false);
        doThrow(new RuntimeException()).when(dbWriter).create(loan);

        CreateLoanResponse expectedResponse = new CreateLoanResponse(false, Message.LOAN_ERROR_MESSAGE);
        CreateLoanQuery query =  new CreateLoanQuery(loanRequestDetails);

        CreateLoanResponse response = queryHandler.execute(query);

        assertNotNull(response);
        assertEquals(expectedResponse.getMessage(), response.getMessage());
        assertEquals(expectedResponse.isCreated(), response.isCreated());
        verify(loanRequestFactory, times(1)).getNewLoanRequest(loanRequestDetails);
        verify(dbWriter, times(1)).create(loanRequest);
        verify(creditExpert, times(1)).hasRisks(loanRequest);
        verify(loanFactory, times(1)).getNewLoan(loanRequest);
        verify(dbWriter, times(1)).create(loan);
    }
}

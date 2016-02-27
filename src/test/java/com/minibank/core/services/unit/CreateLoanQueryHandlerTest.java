package com.minibank.core.services.unit;

import com.minibank.InjectMocksTest;
import com.minibank.common.Messages;
import com.minibank.communications.CreateLoanQuery;
import com.minibank.communications.CreateLoanResponse;
import com.minibank.communications.model.LoanRequestDetails;
import com.minibank.core.factories.LoanFactory;
import com.minibank.core.factories.LoanRequestFactory;
import com.minibank.core.model.Customer;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.LoanRepository;
import com.minibank.core.repositories.LoanRequestRepository;
import com.minibank.core.services.CreateLoanQueryHandler;
import com.minibank.core.services.InputConstraintChecker;
import com.minibank.core.services.LoanExpert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;


public class CreateLoanQueryHandlerTest extends InjectMocksTest {

    @InjectMocks
    private CreateLoanQueryHandler queryHandler;

    @Mock
    private LoanRequestFactory loanRequestFactory;

    @Mock
    private LoanFactory loanFactory;

    @Mock
    private LoanExpert loanExpert;

    @Mock
    private InputConstraintChecker inputConstraintChecker;

    @Mock
    private LoanRequestRepository loanRequestRepository;

    @Mock
    private LoanRepository loanRepository;

    private Customer customer;
    private LoanRequest loanRequest;
    private Loan loan;
    private LoanRequestDetails loanRequestDetails;

    @Before
    public void setUp() {
        customer = mock(Customer.class);
        loanRequest = mock(LoanRequest.class);
        loan = mock(Loan.class);
        loanRequestDetails = mock(LoanRequestDetails.class);

        when(loanRequestFactory.getLoanRequest(any(LoanRequestDetails.class))).thenReturn(loanRequest);
        when(loanRequest.getCustomer()).thenReturn(customer);
        when(loanFactory.getNewLoan(any(LoanRequest.class))).thenReturn(loan);
    }

    @Test
    public void testExecute_1() {
         //Positive path of execution
        //Customer obtains a loan

        when(loanExpert.riskSurroundsLoan(any(LoanRequest.class))).thenReturn(false);
        when(inputConstraintChecker.isEqualOrLessThanMaxLoanAmount(any(BigDecimal.class))).thenReturn(true);

        CreateLoanResponse expectedResponse = new CreateLoanResponse(true, Messages.LOAN_OBTAINED_MESSAGE);
        CreateLoanQuery query = new CreateLoanQuery(loanRequestDetails);

        CreateLoanResponse response = queryHandler.execute(query);
        assertNotNull(response);
        assertEquals(expectedResponse.getMessage(), response.getMessage());
        assertEquals(expectedResponse.isCreated(), response.isCreated());
        verify(loanRequestFactory, times(1)).getLoanRequest(loanRequestDetails);
        verify(loanRequestRepository, times(1)).create(loanRequest);
        verify(inputConstraintChecker, times(1)).isEqualOrLessThanMaxLoanAmount(any(BigDecimal.class));
        verify(loanExpert, times(1)).riskSurroundsLoan(loanRequest);
        verify(loanFactory, times(1)).getNewLoan(loanRequest);
        verify(loanRequest, times(1)).getCustomer();
        verify(loanRepository, times(1)).create(loan);
    }

    @Test
    public void testExecute_2() {
        //Negative path of execution
        //Customer is refused a loan because of the risks surrounding the loan request

        when(loanExpert.riskSurroundsLoan(any(LoanRequest.class))).thenReturn(true);
        when(inputConstraintChecker.isEqualOrLessThanMaxLoanAmount(any(BigDecimal.class))).thenReturn(true);

        CreateLoanResponse expectedResponse = new CreateLoanResponse(false, Messages.LOAN_ERROR_MESSAGE);
        CreateLoanQuery query =  new CreateLoanQuery(loanRequestDetails);

        CreateLoanResponse response = queryHandler.execute(query);

        assertNotNull(response);
        assertEquals(expectedResponse.getMessage(), response.getMessage());
        assertEquals(expectedResponse.isCreated(), response.isCreated());
        verify(loanRequestFactory, times(1)).getLoanRequest(loanRequestDetails);
        verify(loanRequestRepository, times(1)).create(loanRequest);
        verify(inputConstraintChecker, times(1)).isEqualOrLessThanMaxLoanAmount(any(BigDecimal.class));
        verify(loanExpert, times(1)).riskSurroundsLoan(loanRequest);
        verify(loanFactory, times(0)).getNewLoan(loanRequest);
        verify(loanRepository, times(0)).create(loan);
    }

    @Test
    public void testExecute_3() {
        //Negative path of execution
        //Customer is refused a loan because the requested loan amount exceeds the maximum

        when(loanExpert.riskSurroundsLoan(any(LoanRequest.class))).thenReturn(false);
        when(inputConstraintChecker.isEqualOrLessThanMaxLoanAmount(any(BigDecimal.class))).thenReturn(false);

        CreateLoanResponse expectedResponse = new CreateLoanResponse(false, Messages.LOAN_ERROR_MESSAGE);
        CreateLoanQuery query =  new CreateLoanQuery(loanRequestDetails);

        CreateLoanResponse response = queryHandler.execute(query);

        assertNotNull(response);
        assertEquals(expectedResponse.getMessage(), response.getMessage());
        assertEquals(expectedResponse.isCreated(), response.isCreated());
        verify(loanRequestFactory, times(1)).getLoanRequest(loanRequestDetails);
        verify(loanRequestRepository, times(1)).create(loanRequest);
        verify(inputConstraintChecker, times(1)).isEqualOrLessThanMaxLoanAmount(any(BigDecimal.class));
        verify(loanExpert, times(0)).riskSurroundsLoan(loanRequest);
        verify(loanFactory, times(0)).getNewLoan(loanRequest);
        verify(loanRepository, times(0)).create(loan);
    }
}

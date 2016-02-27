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
import com.minibank.core.services.LoanExpert;
import com.minibank.core.validators.LoanAmountValidator;
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
    private LoanAmountValidator loanAmountValidator;

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
    public void testExecuteCustomerObtainsLoan() {
        when(loanExpert.riskSurroundsLoan(any(LoanRequest.class))).thenReturn(false);
        when(loanAmountValidator.isValid(any(BigDecimal.class))).thenReturn(true);

        CreateLoanResponse expectedResponse = new CreateLoanResponse(true, Messages.LOAN_OBTAINED_MESSAGE);
        CreateLoanQuery query = new CreateLoanQuery(loanRequestDetails);

        CreateLoanResponse response = queryHandler.execute(query);
        assertResponse(expectedResponse, response);
        verify(loanRequestFactory, times(1)).getLoanRequest(loanRequestDetails);
        verify(loanRequestRepository, times(1)).create(loanRequest);
        verify(loanAmountValidator, times(1)).isValid(any(BigDecimal.class));
        verify(loanExpert, times(1)).riskSurroundsLoan(loanRequest);
        verify(loanFactory, times(1)).getNewLoan(loanRequest);
        verify(loanRequest, times(1)).getCustomer();
        verify(loanRepository, times(1)).create(loan);
    }

    @Test
    public void testExecuteCustomerDoesNotObtainLoanBecauseOfRisk() {
        when(loanExpert.riskSurroundsLoan(any(LoanRequest.class))).thenReturn(true);
        when(loanAmountValidator.isValid(any(BigDecimal.class))).thenReturn(true);

        CreateLoanResponse expectedResponse = new CreateLoanResponse(false, Messages.LOAN_ERROR_MESSAGE);
        CreateLoanQuery query =  new CreateLoanQuery(loanRequestDetails);

        CreateLoanResponse response = queryHandler.execute(query);

        assertResponse(expectedResponse, response);
        verify(loanRequestFactory, times(1)).getLoanRequest(loanRequestDetails);
        verify(loanRequestRepository, times(1)).create(loanRequest);
        verify(loanAmountValidator, times(1)).isValid(any(BigDecimal.class));
        verify(loanExpert, times(1)).riskSurroundsLoan(loanRequest);
        verify(loanFactory, times(0)).getNewLoan(loanRequest);
        verify(loanRepository, times(0)).create(loan);
    }

    @Test
    public void testExecuteCustomerDoesNotObtainLoanBecauseLoanAmountExceedsMaximum() {
        when(loanExpert.riskSurroundsLoan(any(LoanRequest.class))).thenReturn(false);
        when(loanAmountValidator.isValid(any(BigDecimal.class))).thenReturn(false);

        CreateLoanResponse expectedResponse = new CreateLoanResponse(false, Messages.LOAN_ERROR_MESSAGE);
        CreateLoanQuery query =  new CreateLoanQuery(loanRequestDetails);

        CreateLoanResponse response = queryHandler.execute(query);

        assertResponse(expectedResponse, response);
        verify(loanRequestFactory, times(1)).getLoanRequest(loanRequestDetails);
        verify(loanRequestRepository, times(1)).create(loanRequest);
        verify(loanAmountValidator, times(1)).isValid(any(BigDecimal.class));
        verify(loanExpert, times(0)).riskSurroundsLoan(loanRequest);
        verify(loanFactory, times(0)).getNewLoan(loanRequest);
        verify(loanRepository, times(0)).create(loan);
    }

    private void assertResponse(CreateLoanResponse expectedResponse, CreateLoanResponse actualResponse) {
        assertNotNull(actualResponse);
        assertEquals(expectedResponse.getMessage(), actualResponse.getMessage());
        assertEquals(expectedResponse.isCreated(), actualResponse.isCreated());
    }
}

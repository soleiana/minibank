package com.minibank.core.services.unit;

import com.minibank.InjectMocksTest;
import com.minibank.common.Messages;
import com.minibank.communications.CreateLoanQuery;
import com.minibank.communications.CreateLoanResponse;
import com.minibank.communications.model.LoanRequestDetails;
import com.minibank.core.factories.LoanCoreFactory;
import com.minibank.core.factories.LoanRequestCoreFactory;
import com.minibank.core.model.Customer;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.LoanRepository;
import com.minibank.core.repositories.LoanRequestRepository;
import com.minibank.core.services.CreateLoanQueryHandler;
import com.minibank.core.services.LoanExpert;
import com.minibank.core.validators.LoanAmountValidator;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.mockito.Mockito.*;


public class CreateLoanQueryHandlerTest extends InjectMocksTest {

    @InjectMocks
    private CreateLoanQueryHandler queryHandler;

    @Mock
    private LoanRequestCoreFactory loanRequestCoreFactory;

    @Mock
    private LoanCoreFactory loanCoreFactory;

    @Mock
    private LoanExpert loanExpert;

    @Mock
    private LoanAmountValidator loanAmountValidator;

    @Mock
    private LoanRequestRepository loanRequestRepository;

    @Mock
    private LoanRepository loanRepository;

    @Mock
    private Customer customer;

    @Mock
    private LoanRequest loanRequest;

    @Mock
    private Loan loan;

    @Mock
    private LoanRequestDetails loanRequestDetails;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setUp() {
        when(loanRequestCoreFactory.getLoanRequest(any(LoanRequestDetails.class))).thenReturn(loanRequest);
        when(loanRequest.getCustomer()).thenReturn(customer);
        when(loanCoreFactory.getNewLoan(any(LoanRequest.class))).thenReturn(loan);
    }

    @Test
    public void testExecuteCustomerObtainsLoan() {
        when(loanExpert.riskSurroundsLoan(any(LoanRequest.class))).thenReturn(false);
        when(loanAmountValidator.isValid(any(BigDecimal.class))).thenReturn(true);
        CreateLoanResponse expectedResponse = new CreateLoanResponse(true, Messages.LOAN_OBTAINED_MESSAGE);
        CreateLoanQuery query = new CreateLoanQuery(loanRequestDetails);

        CreateLoanResponse response = queryHandler.execute(query);

        TestUtility.assertResponse(expectedResponse, response);
        verify(loanRequestCoreFactory, times(1)).getLoanRequest(loanRequestDetails);
        verify(loanRequestRepository, times(1)).create(loanRequest);
        verify(loanAmountValidator, times(1)).isValid(any(BigDecimal.class));
        verify(loanExpert, times(1)).riskSurroundsLoan(loanRequest);
        verify(loanCoreFactory, times(1)).getNewLoan(loanRequest);
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

        TestUtility.assertResponse(expectedResponse, response);
        verify(loanRequestCoreFactory, times(1)).getLoanRequest(loanRequestDetails);
        verify(loanRequestRepository, times(1)).create(loanRequest);
        verify(loanAmountValidator, times(1)).isValid(any(BigDecimal.class));
        verify(loanExpert, times(1)).riskSurroundsLoan(loanRequest);
        verify(loanCoreFactory, times(0)).getNewLoan(loanRequest);
        verify(loanRepository, times(0)).create(loan);
    }

    @Test
    public void testExecuteCustomerDoesNotObtainLoanBecauseLoanAmountExceedsMaximum() {
        when(loanExpert.riskSurroundsLoan(any(LoanRequest.class))).thenReturn(false);
        when(loanAmountValidator.isValid(any(BigDecimal.class))).thenReturn(false);
        CreateLoanResponse expectedResponse = new CreateLoanResponse(false, Messages.LOAN_ERROR_MESSAGE);
        CreateLoanQuery query =  new CreateLoanQuery(loanRequestDetails);

        CreateLoanResponse response = queryHandler.execute(query);

        TestUtility.assertResponse(expectedResponse, response);
        verify(loanRequestCoreFactory, times(1)).getLoanRequest(loanRequestDetails);
        verify(loanRequestRepository, times(1)).create(loanRequest);
        verify(loanAmountValidator, times(1)).isValid(any(BigDecimal.class));
        verify(loanExpert, times(0)).riskSurroundsLoan(loanRequest);
        verify(loanCoreFactory, times(0)).getNewLoan(loanRequest);
        verify(loanRepository, times(0)).create(loan);
    }
}

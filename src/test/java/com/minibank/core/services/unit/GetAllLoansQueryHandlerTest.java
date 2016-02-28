package com.minibank.core.services.unit;

import com.minibank.InjectMocksTest;
import com.minibank.communications.GetAllLoansQuery;
import com.minibank.communications.GetAllLoansResponse;
import com.minibank.communications.factories.AllLoansDetailsFactory;
import com.minibank.communications.fixtures.AllLoanDetailsFixture;
import com.minibank.communications.model.AllLoansDetails;
import com.minibank.core.factories.AllLoansCoreFactory;
import com.minibank.core.model.AllLoans;
import com.minibank.core.services.GetAllLoansQueryHandler;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;


public class GetAllLoansQueryHandlerTest extends InjectMocksTest {

    private static final int CUSTOMER_ID = 2;

    @InjectMocks
    private GetAllLoansQueryHandler queryHandler;

    @Mock
    private AllLoansCoreFactory allLoansCoreFactory;

    @Mock
    private AllLoansDetailsFactory allLoansDetailsFactory;

    @Mock
    private AllLoans allLoans;


    @Before
    public void setUp() {
        when(allLoansCoreFactory.getAllLoans(CUSTOMER_ID)).thenReturn(allLoans);
    }

    @Test
    public void testExecuteCustomerObtainsLoanHistory() {
        AllLoansDetails allLoansDetails = AllLoanDetailsFixture.standardAllLoansDetails();
        when(allLoansDetailsFactory.getAllLoansDetails(allLoans)).thenReturn(allLoansDetails);
        GetAllLoansQuery query = new GetAllLoansQuery(CUSTOMER_ID);
        GetAllLoansResponse expectedResponse = new GetAllLoansResponse(allLoansDetails, true);

        GetAllLoansResponse response = queryHandler.execute(query);

        TestUtility.assertResponse(expectedResponse, response);
        verify(allLoansCoreFactory,times(1)).getAllLoans(CUSTOMER_ID);
        verify(allLoansDetailsFactory,times(1)).getAllLoansDetails(allLoans);
    }

    @Test
    public void testExecuteCustomerDoesNotHaveLoanHistory() {
        AllLoansDetails emptyAllLoansDetails = new AllLoansDetails();
        List<com.minibank.communications.model.Loan> emptyLoans = new ArrayList<>();
        emptyAllLoansDetails.setLoans(emptyLoans);
        when(allLoansDetailsFactory.getAllLoansDetails(allLoans)).thenReturn(emptyAllLoansDetails);
        GetAllLoansQuery query = new GetAllLoansQuery(CUSTOMER_ID);
        GetAllLoansResponse expectedResponse = new GetAllLoansResponse(emptyAllLoansDetails, false);

        GetAllLoansResponse response = queryHandler.execute(query);

        TestUtility.assertResponse(expectedResponse, response);
        verify(allLoansCoreFactory,times(1)).getAllLoans(CUSTOMER_ID);
        verify(allLoansDetailsFactory,times(1)).getAllLoansDetails(allLoans);
    }

}

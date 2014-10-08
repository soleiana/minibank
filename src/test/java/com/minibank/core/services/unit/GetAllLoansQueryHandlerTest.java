package com.minibank.core.services.unit;

import com.minibank.InjectMocksTest;
import com.minibank.core.communications.domain.AllLoanDetailsFixture;
import com.minibank.core.communications.loans.GetAllLoansQuery;
import com.minibank.core.communications.loans.GetAllLoansResponse;
import com.minibank.core.communications.loans.domain.AllLoansDetails;
import com.minibank.core.communications.loans.factories.AllLoansDetailsFactory;
import com.minibank.core.domain.AllLoans;
import com.minibank.core.domain.AllLoansFixture;
import com.minibank.core.services.GetAllLoansQueryHandler;
import com.minibank.core.services.factories.AllLoansCoreFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;


/**
 * Created by Ann on 02/10/14.
 */
public class GetAllLoansQueryHandlerTest extends InjectMocksTest
{
    @InjectMocks
    private GetAllLoansQueryHandler queryHandler;
    @Mock
    private AllLoansCoreFactory allLoansCoreFactory;
    @Mock
    private AllLoansDetailsFactory allLoansDetailsFactory;

    private AllLoans allLoans;
    private AllLoansDetails allLoansDetails;
    private Integer customerId;

    @Before
    public void setUp()
    {
        customerId = 1;
        allLoans = AllLoansFixture.standardAllLoans();
        allLoansDetails = AllLoanDetailsFixture.standardAllLoansDetails();

        when(allLoansCoreFactory.getNewAllLoans(customerId)).thenReturn(allLoans);
    }

    @Test
    public void testExecute_1()
    {
        //Positive path of execution
        //Customer obtains loan history

        when(allLoansDetailsFactory.getNewAllLoansDetails(allLoans)).thenReturn(allLoansDetails);

        GetAllLoansQuery query = new GetAllLoansQuery(customerId);
        GetAllLoansResponse expectedResponse = new GetAllLoansResponse(allLoansDetails,true);

        GetAllLoansResponse response = queryHandler.execute(query);

        assertNotNull(response);
        assertEquals(expectedResponse.getAllLoansDetails(), response.getAllLoansDetails());
        assertEquals(expectedResponse.isEntityFound(), response.isEntityFound());
        verify(allLoansCoreFactory,times(1)).getNewAllLoans(customerId);
        verify(allLoansDetailsFactory,times(1)).getNewAllLoansDetails(allLoans);
    }

    @Test
    public void testExecute_2()
    {
        //Negative path of execution
        //Customer doesn't obtain loan history because he does not have it

        AllLoansDetails emptyAllLoansDetails = new AllLoansDetails();
        List<com.minibank.core.communications.loans.domain.Loan> emptyLoans = new ArrayList<>();
        emptyAllLoansDetails.setLoans(emptyLoans);

        when(allLoansDetailsFactory.getNewAllLoansDetails(allLoans)).thenReturn(emptyAllLoansDetails);

        GetAllLoansQuery query = new GetAllLoansQuery(customerId);
        GetAllLoansResponse expectedResponse = new GetAllLoansResponse(emptyAllLoansDetails,false);

        GetAllLoansResponse response = queryHandler.execute(query);

        assertNotNull(response);
        assertEquals(expectedResponse.getAllLoansDetails(), response.getAllLoansDetails());
        assertEquals(expectedResponse.isEntityFound(), response.isEntityFound());
        verify(allLoansCoreFactory,times(1)).getNewAllLoans(customerId);
        verify(allLoansDetailsFactory,times(1)).getNewAllLoansDetails(allLoans);
    }

    @Test
    public void testExecute_3()
    {
        //Negative path of execution
        //Customer doesn't obtain loan history because of the database failure

        when(allLoansDetailsFactory.getNewAllLoansDetails(allLoans)).thenReturn(allLoansDetails);

        doThrow(new RuntimeException()).when(allLoansCoreFactory).getNewAllLoans(customerId);

        AllLoansDetails nullAllLoansDetails = null;

        GetAllLoansQuery query = new GetAllLoansQuery(customerId);
        GetAllLoansResponse expectedResponse = new GetAllLoansResponse(nullAllLoansDetails,false);

        GetAllLoansResponse response = queryHandler.execute(query);

        assertNotNull(response);
        assertEquals(expectedResponse.getAllLoansDetails(), response.getAllLoansDetails());
        assertEquals(expectedResponse.isEntityFound(), response.isEntityFound());
        verify(allLoansCoreFactory,times(1)).getNewAllLoans(customerId);
        verify(allLoansDetailsFactory,times(0)).getNewAllLoansDetails(allLoans);
    }
}

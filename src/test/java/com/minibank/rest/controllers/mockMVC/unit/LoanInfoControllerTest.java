package com.minibank.rest.controllers.mockMVC.unit;

import com.minibank.core.communications.loans.GetAllLoansQuery;
import com.minibank.core.communications.loans.GetAllLoansResponse;
import com.minibank.core.communications.loans.domain.AllLoansDetails;
import com.minibank.core.services.QueryExecutor;
import com.minibank.rest.controllers.LoanInfoController;
import com.minibank.rest.domain.AllLoans;
import com.minibank.rest.factories.AllLoansRestFactory;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.mockito.Mockito.*;

import static com.minibank.rest.domain.JsonDataFixture.*;
import static com.minibank.rest.domain.AllLoansFixture.*;

/**
 * Created by Ann on 16/09/14.
 */
public class LoanInfoControllerTest
{
    MockMvc mockMvc;

    @InjectMocks
    LoanInfoController loanInfoController;

    @Mock
    QueryExecutor queryExecutor;

    @Mock
    AllLoansRestFactory allLoansRestFactory;

    @Before
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(loanInfoController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void testThatLoanInfoControllerUsesHttpOkOnSuccess() throws Exception
    {
        when(queryExecutor.execute(any(GetAllLoansQuery.class)))
                .thenReturn(new GetAllLoansResponse(new AllLoansDetails(), true));
        when(allLoansRestFactory.getNewAllLoans(any(AllLoansDetails.class)))
                .thenReturn(new AllLoans());

        this.mockMvc.perform(
                get("/customers/{id}/loans", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testThatLoanInfoControllerRendersCorrectly() throws Exception
    {
        when(queryExecutor.execute(any(GetAllLoansQuery.class)))
                .thenReturn(new GetAllLoansResponse(new AllLoansDetails(), true));
        when(allLoansRestFactory.getNewAllLoans(any(AllLoansDetails.class)))
                .thenReturn(standardAllLoans());

        this.mockMvc.perform(
                get("/customers/{id}/loans", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(standardAllLoansResponseJSON()));

    }

    @Test
    public void testThatLoanInfoControllerUsesHttpNotFoundOnFailure() throws Exception
    {
        when(queryExecutor.execute(any(GetAllLoansQuery.class)))
                .thenReturn(new GetAllLoansResponse(new AllLoansDetails(), false));
        when(allLoansRestFactory.getNewAllLoans(any(AllLoansDetails.class)))
                .thenReturn(new AllLoans());

        this.mockMvc.perform(
                get("/customers/{id}/loans", 1))
                .andExpect(status().isNotFound());
    }
}

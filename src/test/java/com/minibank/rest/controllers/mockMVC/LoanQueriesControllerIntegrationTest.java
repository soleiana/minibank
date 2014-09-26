package com.minibank.rest.controllers.mockMVC;

import com.minibank.core.events.loans.AllLoansEvent;
import com.minibank.core.events.loans.RequestAllLoansEvent;
import com.minibank.core.events.loans.domain.AllLoansDetails;
import com.minibank.rest.controllers.LoanQueriesController;
import com.minibank.rest.domain.AllLoans;
import com.minibank.rest.factories.AllLoansFactory;
import org.junit.Before;
import org.junit.Test;
import com.minibank.core.services.LoanService;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Qualifier;
import javax.ws.rs.core.MediaType;
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
public class LoanQueriesControllerIntegrationTest
{
    MockMvc mockMvc;

    @InjectMocks
    LoanQueriesController loanQueriesController;
    @Mock
    LoanService loanService;
    @Mock
    @Qualifier("Rest")
    AllLoansFactory allLoansFactory;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(loanQueriesController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void testThatLoanQueriesControllerUsesHttpOkOnSuccess() throws Exception
    {
        when(loanService.requestAllLoans(any(RequestAllLoansEvent.class)))
                .thenReturn(new AllLoansEvent(new AllLoansDetails(), true));
        when(allLoansFactory.getNewAllLoans(any(AllLoansDetails.class)))
                .thenReturn(new AllLoans());

        this.mockMvc.perform(
                get("/rest/customers/{id}/loans", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testThatLoanQueriesControllerRendersCorrectly() throws Exception
    {
        when(loanService.requestAllLoans(any(RequestAllLoansEvent.class)))
                .thenReturn(new AllLoansEvent(new AllLoansDetails(), true));
        when(allLoansFactory.getNewAllLoans(any(AllLoansDetails.class)))
                .thenReturn(standardAllLoans());

        this.mockMvc.perform(
                get("/rest/customers/{id}/loans", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(standardAllLoansResponseJSON()));

    }

    @Test
    public void testThatLoanQueriesControllerUsesHttpNotFoundOnFailure() throws Exception
    {
        when(loanService.requestAllLoans(any(RequestAllLoansEvent.class)))
                .thenReturn(new AllLoansEvent(new AllLoansDetails(),false));
        when(allLoansFactory.getNewAllLoans(any(AllLoansDetails.class)))
                .thenReturn(new AllLoans());

        this.mockMvc.perform(
                get("/rest/customers/{id}/loans", 1))
                .andExpect(status().isNotFound());
    }
}

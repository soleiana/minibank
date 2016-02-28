package com.minibank.rest.controllers.unit;

import com.minibank.communications.GetAllLoansQuery;
import com.minibank.communications.GetAllLoansResponse;
import com.minibank.communications.model.AllLoansDetails;
import com.minibank.core.services.GetAllLoansQueryHandler;
import com.minibank.rest.controllers.LoanInfoController;
import com.minibank.rest.factories.AllLoansRestFactory;
import com.minibank.rest.model.AllLoans;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static com.minibank.rest.fixtures.AllLoansFixture.standardAllLoans;
import static com.minibank.rest.fixtures.JsonAllLoansFixture.standardAllLoansResponse;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class LoanInfoControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    LoanInfoController loanInfoController;

    @Mock
    GetAllLoansQueryHandler getAllLoansQueryHandler;

    @Mock
    AllLoansRestFactory allLoansRestFactory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(loanInfoController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void testLoanInfoControllerUsesHttpOkOnSuccess() throws Exception {
        when(getAllLoansQueryHandler.execute(any(GetAllLoansQuery.class))).thenReturn(new GetAllLoansResponse(new AllLoansDetails(), true));
        when(allLoansRestFactory.getAllLoans(any(AllLoansDetails.class))).thenReturn(new AllLoans());

        this.mockMvc.perform(
                get("/customers/{id}/loans", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testLoanInfoControllerRendersResponseCorrectly() throws Exception {
        when(getAllLoansQueryHandler.execute(any(GetAllLoansQuery.class))).thenReturn(new GetAllLoansResponse(new AllLoansDetails(), true));
        when(allLoansRestFactory.getAllLoans(any(AllLoansDetails.class))).thenReturn(standardAllLoans());

        this.mockMvc.perform(
                get("/customers/{id}/loans", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(standardAllLoansResponse()));

    }

    @Test
    public void testLoanInfoControllerUsesHttpNotFoundOnFailure() throws Exception {
        when(getAllLoansQueryHandler.execute(any(GetAllLoansQuery.class))).thenReturn(new GetAllLoansResponse(new AllLoansDetails(), false));
        when(allLoansRestFactory.getAllLoans(any(AllLoansDetails.class))).thenReturn(new AllLoans());

        this.mockMvc.perform(
                get("/customers/{id}/loans", 1))
                .andExpect(status().isNotFound());
    }
}
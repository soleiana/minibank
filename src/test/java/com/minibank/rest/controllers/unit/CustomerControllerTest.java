package com.minibank.rest.controllers.unit;

import com.minibank.InjectMocksTest;
import com.minibank.communications.GetAllLoansQuery;
import com.minibank.communications.GetAllLoansResponse;
import com.minibank.communications.model.AllLoansDetails;
import com.minibank.core.services.GetAllLoansQueryHandler;
import com.minibank.rest.controllers.CustomerController;
import com.minibank.rest.factories.AllLoansRestFactory;
import com.minibank.rest.model.AllLoans;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static com.minibank.rest.fixtures.AllLoansFixture.emptyAllLoans;
import static com.minibank.rest.fixtures.AllLoansFixture.standardAllLoans;
import static com.minibank.rest.fixtures.JsonAllLoansFixture.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class CustomerControllerTest extends InjectMocksTest {

    private MockMvc mockMvc;

    @InjectMocks
    private CustomerController customerController;

    @Mock
    private GetAllLoansQueryHandler getAllLoansQueryHandler;

    @Mock
    private AllLoansRestFactory allLoansRestFactory;

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(customerController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void testCustomerControllerUsesHttpOkOnSuccess() throws Exception {
        when(getAllLoansQueryHandler.execute(any(GetAllLoansQuery.class)))
                .thenReturn(new GetAllLoansResponse(new AllLoansDetails(), true));
        when(allLoansRestFactory.getAllLoans(any(AllLoansDetails.class))).thenReturn(new AllLoans());

        this.mockMvc.perform(
                get("/customers/{id}/loans", 1))
                .andExpect(status().isOk());
    }

    @Test
    public void testCustomerControllerUsesHttpBadRequestIfCustomerIdIsNotInteger() throws Exception {
        this.mockMvc.perform(
                get("/customers/{id}/loans", "1a"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCustomerControllerRendersResponseCorrectly() throws Exception {
        when(getAllLoansQueryHandler.execute(any(GetAllLoansQuery.class)))
                .thenReturn(new GetAllLoansResponse(new AllLoansDetails(), true));
        when(allLoansRestFactory.getAllLoans(any(AllLoansDetails.class))).thenReturn(standardAllLoans());

        this.mockMvc.perform(
                get("/customers/{id}/loans", 2))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(standardAllLoansResponse()));
    }

    @Test
    public void testCustomerControllerUsesHttpNotFoundIfNoLoansFound() throws Exception {
        when(getAllLoansQueryHandler.execute(any(GetAllLoansQuery.class)))
                .thenReturn(new GetAllLoansResponse(new AllLoansDetails(), false));
        when(allLoansRestFactory.getAllLoans(any(AllLoansDetails.class))).thenReturn(emptyAllLoans());

        this.mockMvc.perform(
                get("/customers/{id}/loans", 1))
                .andExpect(status().isNotFound())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(loansNotFoundResponse()));
    }

    @Test
    public void testCustomerControllerUsesHttpInternalServerErrorOnFailureToGetLoans() throws Exception {
        when(getAllLoansQueryHandler.execute(any(GetAllLoansQuery.class)))
                .thenReturn(new GetAllLoansResponse(true));

        this.mockMvc.perform(
                get("/customers/{id}/loans", 1))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(content().string(emptyAllResponse()));
    }
}

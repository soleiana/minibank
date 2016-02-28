package com.minibank.rest.controllers.unit;

import com.minibank.InjectMocksTest;
import com.minibank.common.Messages;
import com.minibank.communications.CreateLoanQuery;
import com.minibank.communications.CreateLoanResponse;
import com.minibank.communications.factories.LoanRequestDetailsFactory;
import com.minibank.communications.model.LoanRequestDetails;
import com.minibank.core.services.CreateLoanQueryHandler;
import com.minibank.rest.controllers.LoanController;
import com.minibank.rest.model.LoanRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static com.minibank.rest.fixtures.JsonLoanRequestFixture.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class LoanControllerTest extends InjectMocksTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LoanController loanController;

    @Mock
    private CreateLoanQueryHandler createLoanQueryHandler;

    @Mock
    private LoanRequestDetailsFactory loanRequestDetailsFactory;

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(loanController)
              .setMessageConverters(new MappingJackson2HttpMessageConverter())
              .build();
    }

    @Test
    public void testCreateLoanUsesHttpCreatedOnSuccess() throws Exception {
        when(loanRequestDetailsFactory.getLoanRequestDetails(any(LoanRequest.class))).thenReturn(new LoanRequestDetails());
        when(createLoanQueryHandler.execute(any(CreateLoanQuery.class))).thenReturn(new CreateLoanResponse(true, Messages.LOAN_OBTAINED_MESSAGE));

        this.mockMvc.perform(
              post("/loans")
                .content(standardLoanRequest())
                .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isCreated());
    }

    @Test
    public void testCreateLoanUsesHttpInternalServerErrorOnFailureToGetLoan() throws Exception {
        when(loanRequestDetailsFactory.getLoanRequestDetails(any(LoanRequest.class))).thenReturn(new LoanRequestDetails());
        when(createLoanQueryHandler.execute(any(CreateLoanQuery.class))).thenReturn(new CreateLoanResponse(false, Messages.LOAN_ERROR_MESSAGE));

        this.mockMvc.perform(
                post("/loans")
                        .content(standardLoanRequest())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void testCreateLoanUsesHttpBadRequestOnFailureToValidateNullCustomerId() throws Exception {
        this.mockMvc.perform(
                post("/loans")
                        .content(loanRequestWithNullCustomerId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateLoanUsesHttpBadRequestOnFailureToValidateNegativeCustomerId() throws Exception {
        this.mockMvc.perform(
                post("/loans")
                        .content(loanRequestWithNegativeCustomerId())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateLoanUsesHttpBadRequestOnFailureToValidateTermLessThanMin() throws Exception {
        this.mockMvc.perform(
                post("/loans")
                        .content(loanRequestWithTermLessThanMin())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateLoanUsesHttpBadRequestOnFailureToValidateTermMoreThanMax() throws Exception {
        this.mockMvc.perform(
                post("/loans")
                        .content(loanRequestWithTermMoreThanMax())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateLoanUsesHttpBadRequestOnFailureToValidateAmountLessThanMin() throws Exception {
        this.mockMvc.perform(
                post("/loans")
                        .content(loanRequestWithAmountLessThanMin())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testCreateLoanUsesHttpBadRequestOnFailureToValidateAmountMoreThanMax() throws Exception {
        this.mockMvc.perform(
                post("/loans")
                        .content(loanRequestWithAmountMoreThanMax())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

}

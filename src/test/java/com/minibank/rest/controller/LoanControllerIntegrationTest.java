package com.minibank.rest.controller;

import com.minibank.core.events.CreatedEvent;
import com.minibank.core.events.loans.CreateLoanEvent;
import com.minibank.core.events.loans.domain.LoanRequestDetails;
import com.minibank.core.events.loans.factories.LoanRequestDetailsFactory;
import com.minibank.core.services.LoanService;
import com.minibank.core.services.common.Message;
import com.minibank.rest.domain.LoanRequest;
import org.junit.Before;
import org.junit.Test;
import org.hamcrest.Matchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.mockito.Mockito.*;

import static com.minibank.rest.domain.RestEventFixtures.*;
import static com.minibank.rest.domain.RestDataFixture.*;


/**
 * Created by Ann on 16/09/14.
 */
public class LoanControllerIntegrationTest
{
    MockMvc mockMvc;

    @InjectMocks
    LoanController loanController;
    @Mock
    LoanService loanService;
    @Mock
    LoanRequestDetailsFactory loanRequestDetailsFactory;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(loanController)
              .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void testThatCreateLoanUsesHttpCreatedOnSuccess() throws Exception
    {
        when(loanService.createLoan(any(CreateLoanEvent.class)))
                .thenReturn(loanCreated(true, Message.LOAN_OBTAINED_MESSAGE));
        when(loanRequestDetailsFactory.getNewLoanRequestDetails(any(LoanRequest.class)))
                .thenReturn(new LoanRequestDetails());

        this.mockMvc.perform(
              post("/rest/loans")
                .content(standardLoanRequestJSON())
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
              .andExpect(status().isCreated());
    }

    @Test
    public void testThatCreateLoanUsesHttpForbiddenOnFailure() throws Exception
    {
        when(loanService.createLoan(any(CreateLoanEvent.class)))
                .thenReturn(loanCreated(false, Message.LOAN_ERROR_MESSAGE));
        when(loanRequestDetailsFactory.getNewLoanRequestDetails(any(LoanRequest.class)))
                .thenReturn(new LoanRequestDetails());

        this.mockMvc.perform(
                post("/rest/loans")
                        .content(standardLoanRequestJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }
}

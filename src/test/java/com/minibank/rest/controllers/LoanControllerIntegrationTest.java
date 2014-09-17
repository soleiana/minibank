package com.minibank.rest.controllers;

import com.minibank.core.events.loans.CreateLoanEvent;
import com.minibank.core.events.loans.LoanCreatedEvent;
import com.minibank.core.events.loans.domain.LoanRequestDetails;
import com.minibank.core.events.loans.factories.LoanRequestDetailsFactory;
import com.minibank.core.services.LoanService;
import com.minibank.core.services.common.Message;
import com.minibank.rest.domain.LoanRequest;
import com.minibank.rest.validators.LoanRequestValidator;
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
    LoanRequestValidator loanRequestValidator;
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
        when(loanRequestValidator.validate(any(LoanRequest.class)))
                .thenReturn(true);
        when(loanService.createLoan(any(CreateLoanEvent.class)))
                .thenReturn(new LoanCreatedEvent(true, Message.LOAN_OBTAINED_MESSAGE));
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
    public void testThatCreateLoanUsesHttpForbiddenOnFailureToGetLoan() throws Exception
    {
        when(loanRequestValidator.validate(any(LoanRequest.class)))
                .thenReturn(true);
        when(loanService.createLoan(any(CreateLoanEvent.class)))
                .thenReturn(new LoanCreatedEvent(false,Message.LOAN_ERROR_MESSAGE));
        when(loanRequestDetailsFactory.getNewLoanRequestDetails(any(LoanRequest.class)))
                .thenReturn(new LoanRequestDetails());

        this.mockMvc.perform(
                post("/rest/loans")
                        .content(standardLoanRequestJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

    @Test
    public void testThatCreateLoanUsesHttpForbiddenOnFailureToValidate_1() throws Exception
    {
        this.mockMvc.perform(
                post("/rest/loans")
                        .content(wrongLoanRequestJSON_1())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }

    @Test
    public void testThatCreateLoanUsesHttpForbiddenOnFailureToValidate_2() throws Exception
    {
        this.mockMvc.perform(
                post("/rest/loans")
                        .content(wrongLoanRequestJSON_2())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isForbidden());

    }
}

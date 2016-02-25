package com.minibank.rest.controllers.mockMVC.unit;

import com.minibank.communications.CreateLoanQuery;
import com.minibank.communications.CreateLoanResponse;
import com.minibank.communications.model.LoanRequestDetails;
import com.minibank.communications.factories.LoanRequestDetailsFactory;
import com.minibank.core.services.CreateLoanQueryHandler;
import com.minibank.core.services.common.Message;
import com.minibank.rest.controllers.LoanController;
import com.minibank.rest.model.LoanRequest;
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

import static com.minibank.rest.model.JsonDataFixture.*;


public class LoanControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    LoanController loanController;

    @Mock
    LoanRequestValidator loanRequestValidator;

    @Mock
    CreateLoanQueryHandler createLoanQueryHandler;

    @Mock
    LoanRequestDetailsFactory loanRequestDetailsFactory;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(loanController)
              .setMessageConverters(new MappingJackson2HttpMessageConverter())
              .build();
    }

    @Test
    public void testThatCreateLoanUsesHttpCreatedOnSuccess() throws Exception {
        when(loanRequestValidator.validate(any(LoanRequest.class))).thenReturn(true);
        when(loanRequestDetailsFactory.getLoanRequestDetails(any(LoanRequest.class))).thenReturn(new LoanRequestDetails());
        when(createLoanQueryHandler.execute(any(CreateLoanQuery.class))).thenReturn(new CreateLoanResponse(true, Message.LOAN_OBTAINED_MESSAGE));

        this.mockMvc.perform(
              post("/loans")
                .content(standardLoanRequestJSON())
                .contentType(MediaType.APPLICATION_JSON))
              .andExpect(status().isCreated());
    }

    @Test
    public void testThatCreateLoanUsesHttpInternalServerErrorOnFailureToGetLoan() throws Exception {
        when(loanRequestValidator.validate(any(LoanRequest.class))).thenReturn(true);
        when(loanRequestDetailsFactory.getLoanRequestDetails(any(LoanRequest.class))).thenReturn(new LoanRequestDetails());
        when(createLoanQueryHandler.execute(any(CreateLoanQuery.class))).thenReturn(new CreateLoanResponse(false, Message.LOAN_ERROR_MESSAGE));

        this.mockMvc.perform(
                post("/loans")
                        .content(standardLoanRequestJSON())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError());

    }

    @Test
    public void testThatCreateLoanUsesHttpBadRequestOnFailureToValidate() throws Exception {
        when(loanRequestValidator.validate(any(LoanRequest.class))).thenReturn(false);
        this.mockMvc.perform(
                post("/loans")
                        .content(standardLoanRequestJSON())
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

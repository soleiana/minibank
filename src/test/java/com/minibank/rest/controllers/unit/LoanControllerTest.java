package com.minibank.rest.controllers.unit;

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
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static com.minibank.rest.fixtures.JsonLoanRequestFixture.standardLoanRequest;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class LoanControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    LoanController loanController;

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

}

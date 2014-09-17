package com.minibank.rest.controller;

import com.minibank.core.events.loans.CreateLoanExtensionEvent;
import com.minibank.core.events.loans.LoanExtensionCreatedEvent;
import org.junit.Before;
import org.junit.Test;
import com.minibank.core.services.LoanService;
import com.minibank.core.services.common.Message;
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


/**
 * Created by Ann on 16/09/14.
 */
public class LoanExtensionControllerIntegrationTest
{
    MockMvc mockMvc;

    @InjectMocks
    LoanExtensionController loanExtensionController;
    @Mock
    LoanService loanService;

    @Before
    public void setUp() throws Exception
    {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(loanExtensionController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void testThatCreateLoanExtensionUsesHttpCreated() throws Exception
    {
        when(loanService.createLoanExtension(any(CreateLoanExtensionEvent.class)))
                .thenReturn(new LoanExtensionCreatedEvent(Message.LOAN_EXTENSION_MESSAGE));
        this.mockMvc.perform(
                post("/rest/loans/loanExtensions")
                        .content("1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated());
    }
}

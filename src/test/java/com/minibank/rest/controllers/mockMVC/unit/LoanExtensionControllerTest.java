package com.minibank.rest.controllers.mockMVC.unit;

import com.minibank.common.Messages;
import com.minibank.communications.CreateLoanExtensionQuery;
import com.minibank.communications.CreateLoanExtensionResponse;
import com.minibank.core.services.CreateLoanExtensionQueryHandler;
import com.minibank.rest.controllers.LoanExtensionController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class LoanExtensionControllerTest {

    MockMvc mockMvc;

    @InjectMocks
    LoanExtensionController loanExtensionController;

    @Mock
    CreateLoanExtensionQueryHandler createLoanExtensionQueryHandler;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = standaloneSetup(loanExtensionController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void testThatCreateLoanExtensionUsesHttpCreated() throws Exception {
        when(createLoanExtensionQueryHandler.execute(any(CreateLoanExtensionQuery.class))).thenReturn(new CreateLoanExtensionResponse(true, Messages.LOAN_EXTENSION_OBTAINED_MESSAGE));
        this.mockMvc.perform(
                  post("/loans/1/extensions")
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isCreated());
    }

    @Test
    public void testThatCreateLoanExtensionUsesHttpBadRequest_1() throws Exception {
        this.mockMvc.perform(
                post("/loans/null/extensions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void testThatCreateLoanExtensionUsesHttpBadRequest_2() throws Exception {
        this.mockMvc.perform(
                post("/loans/-1/extensions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

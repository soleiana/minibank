package com.minibank.rest.controllers.unit;

import com.minibank.InjectMocksTest;
import com.minibank.common.Messages;
import com.minibank.communications.CreateLoanExtensionQuery;
import com.minibank.communications.CreateLoanExtensionResponse;
import com.minibank.core.services.CreateLoanExtensionQueryHandler;
import com.minibank.rest.controllers.LoanExtensionController;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;


public class LoanExtensionControllerTest extends InjectMocksTest {

    private MockMvc mockMvc;

    @InjectMocks
    private LoanExtensionController loanExtensionController;

    @Mock
    private CreateLoanExtensionQueryHandler createLoanExtensionQueryHandler;

    @Before
    public void setUp() {
        this.mockMvc = standaloneSetup(loanExtensionController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void testCreateLoanExtensionUsesHttpCreated() throws Exception {
        when(createLoanExtensionQueryHandler.execute(any(CreateLoanExtensionQuery.class)))
                .thenReturn(new CreateLoanExtensionResponse(true, Messages.LOAN_EXTENSION_OBTAINED_MESSAGE));
        this.mockMvc.perform(
                  post("/loans/1/extensions")
                  .contentType(MediaType.APPLICATION_JSON))
                  .andExpect(status().isCreated())
                  .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateLoanExtensionUsesHttpHttpInternalServerErrorOnFailureToExtendLoan() throws Exception {
        when(createLoanExtensionQueryHandler.execute(any(CreateLoanExtensionQuery.class)))
                .thenReturn(new CreateLoanExtensionResponse(false, Messages.LOAN_EXTENSION_ERROR_MESSAGE));
        this.mockMvc.perform(
                post("/loans/1/extensions")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
    }

    @Test
    public void testCreateLoanExtensionUsesHttpBadRequestIfLoanIdIsNotInteger() throws Exception {
        this.mockMvc.perform(
                post("/loans/1a/extensions")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }
}

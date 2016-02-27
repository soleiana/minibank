package com.minibank.core.rules;

import com.minibank.InjectMocksTest;
import com.minibank.core.common.AppParametersProvider;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;


public class MaxLoanAmountRuleTest extends InjectMocksTest {

    private static final BigDecimal MAX_LOAN_AMOUNT = BankParametersFixture.STANDARD_MAX_LOAN_AMOUNT;

    @InjectMocks
    private MaxLoanAmountRule rule;

    @Mock
    private AppParametersProvider parametersProvider;

    @Mock
    private BankParameters bankParameters;
    private LoanRequest loanRequest;

    @Before
    public void setUp() {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        when(parametersProvider.getBankParameters()).thenReturn(bankParameters);
        when(bankParameters.getMaxLoanAmount()).thenReturn(MAX_LOAN_AMOUNT);
    }

    @Test
    public void testHoldsTrueIsTrueIfMaxLoanAmount() {
        loanRequest.setAmount(MAX_LOAN_AMOUNT);
        assertTrue(rule.holdsTrue(loanRequest));
    }

    @Test
    public void testHoldsTrueIsFalseIfLessThanMaxLoanAmount() {
        loanRequest.setAmount(MAX_LOAN_AMOUNT.subtract(new BigDecimal("1.00")));
        assertFalse(rule.holdsTrue(loanRequest));
    }
}
package com.minibank.core.validators;

import com.minibank.InjectMocksTest;
import com.minibank.core.common.AppParametersProvider;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.model.BankParameters;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class LoanAmountValidatorTest extends InjectMocksTest {

    private static final BigDecimal MAX_LOAN_AMOUNT = BankParametersFixture.STANDARD_MAX_LOAN_AMOUNT;

    @InjectMocks
    private LoanAmountValidator validator;

    @Mock
    private AppParametersProvider parametersProvider;

    @Mock
    private BankParameters bankParameters;

    @Before
    public void setUp() {
        when(parametersProvider.getBankParameters()).thenReturn(bankParameters);
        when(bankParameters.getMaxLoanAmount()).thenReturn(MAX_LOAN_AMOUNT);
    }

    @Test
    public void testIsValidIsTrueIfLoanAmountIsLessThanMaximum() {
        assertTrue(validator.isValid(MAX_LOAN_AMOUNT.subtract(new BigDecimal("1.00"))));
    }

    @Test
    public void testIsValidIsTrueIfLoanAmountIsEqualToMaximum() {
        assertTrue(validator.isValid(MAX_LOAN_AMOUNT));
    }

    @Test
    public void testIsValidIsFalseIfLoanAmountIsGraterThanMaximum() {
        assertFalse(validator.isValid(MAX_LOAN_AMOUNT.add(new BigDecimal("1.00"))));
    }
}
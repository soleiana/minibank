package com.minibank.core.calculators.unit;

import com.minibank.InjectMocksTest;
import com.minibank.core.calculators.LoanExtensionCalculator;
import com.minibank.core.common.AppParametersProvider;
import com.minibank.core.fixtures.LoanFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.Loan;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class LoanExtensionCalculatorTest extends InjectMocksTest {

    @InjectMocks
    private LoanExtensionCalculator loanExtensionCalculator;

    @Mock
    private AppParametersProvider parametersProvider;

    @Mock
    private BankParameters bankParameters;

    @Before
    public void setUp() {
        when(parametersProvider.getBankParameters()).thenReturn(bankParameters);
    }

    @Test
    public void testGetLoanExtensionEndDate() {
        final short LOAN_EXTENSION_TERM = 7;
        when(bankParameters.getLoanExtensionTerm()).thenReturn(LOAN_EXTENSION_TERM);
        Loan loan = LoanFixture.standardLoan();
        LocalDate loanEndDate = LocalDate.now();
        loan.setEndDate(loanEndDate);
        LocalDate loanExtensionEndDate = loanExtensionCalculator.getLoanExtensionEndDate(loan);
        assertEquals(Period.ofDays(LOAN_EXTENSION_TERM), loanEndDate.until(loanExtensionEndDate));
    }

    @Test
    public void testGetNewInterestRate() {
        BigDecimal currentInterestRate = new BigDecimal("100.00");
        BigDecimal factor = new BigDecimal("1.5");
        when(bankParameters.getInterestRateFactor()).thenReturn(factor);
        BigDecimal expectedInterestRate = currentInterestRate
                .multiply(factor)
                .setScale(2 ,BigDecimal.ROUND_HALF_EVEN);
        BigDecimal newInterestRate = loanExtensionCalculator.getNewInterestRate(currentInterestRate);
        assertTrue(newInterestRate.compareTo(expectedInterestRate) == 0);
    }
}

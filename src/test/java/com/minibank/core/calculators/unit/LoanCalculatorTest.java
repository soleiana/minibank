package com.minibank.core.calculators.unit;

import com.minibank.InjectMocksTest;
import com.minibank.core.calculators.CalculationUtility;
import com.minibank.core.calculators.LoanCalculator;
import com.minibank.core.common.AppParametersProvider;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class LoanCalculatorTest extends InjectMocksTest {

    @InjectMocks
    private LoanCalculator loanCalculator;

    @Mock
    private AppParametersProvider parametersProvider;

    @Mock
    private CalculationUtility calculationUtility;

    @Mock
    private BankParameters bankParameters;

    @Before
    public void setUp() {
        when(parametersProvider.getBankParameters()).thenReturn(bankParameters);
    }

    @Test
    public void testGetLoanEndDate() {
        final int TERM = 20;
        LoanRequest loanRequest = LoanRequestFixture.standardLoanRequest();
        LocalDate loanRequestSubmissionDate = LocalDate.now();
        loanRequest.setSubmissionDate(loanRequestSubmissionDate);
        loanRequest.setTerm(TERM);
        LocalDate loanEndDate = loanCalculator.getLoanEndDate(loanRequest);
        assertEquals(Period.ofDays(TERM), loanRequestSubmissionDate.until(loanEndDate));
    }
}

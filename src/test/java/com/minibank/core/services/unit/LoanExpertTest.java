package com.minibank.core.services.unit;

import com.minibank.InjectMocksTest;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.rules.LoanRequestSubmissionTimeRule;
import com.minibank.core.rules.MaxLoanAmountRule;
import com.minibank.core.rules.MaxLoanAttemptsPerIpRule;
import com.minibank.core.services.LoanExpert;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class LoanExpertTest extends InjectMocksTest {

    @InjectMocks
    private LoanExpert loanExpert;

    @Mock
    private MaxLoanAmountRule maxLoanAmountRule;

    @Mock
    private MaxLoanAttemptsPerIpRule maxLoanAttemptsPerIpRule;

    @Mock
    private LoanRequestSubmissionTimeRule loanRequestSubmissionTimeRule;

    @Mock
    private LoanRequest loanRequest;

    @Test
    public void tesRiskSurroundsLoanIsTrueIfMaxLoanAmountAndRiskSubmissionTime(){
        when(maxLoanAttemptsPerIpRule.holdsTrue(loanRequest)).thenReturn(false);
        when(maxLoanAmountRule.holdsTrue(loanRequest)).thenReturn(true);
        when(loanRequestSubmissionTimeRule.holdsTrue(loanRequest)).thenReturn(true);
        assertTrue(loanExpert.riskSurroundsLoan(loanRequest));
    }

    @Test
    public void testRiskSurroundsLoanIsTrueIfMoreThanMaxLoanAttemptsPerIpDuringOneDay() {
        when(maxLoanAttemptsPerIpRule.holdsTrue(loanRequest)).thenReturn(true);
        assertTrue(loanExpert.riskSurroundsLoan(loanRequest));
    }

    @Test
    public void testRiskSurroundsLoanIsFalseIfLessThanMaxLoanAmountAndNonRiskSubmissionTime() {
        when(maxLoanAttemptsPerIpRule.holdsTrue(loanRequest)).thenReturn(false);
        when(maxLoanAmountRule.holdsTrue(loanRequest)).thenReturn(false);
        when(loanRequestSubmissionTimeRule.holdsTrue(loanRequest)).thenReturn(false);
        assertFalse(loanExpert.riskSurroundsLoan(loanRequest));
    }

    @Test
    public void testRiskSurroundsLoanIsFalseIfMaxLoanAmountAndNonRiskSubmissionTime() {
        when(maxLoanAttemptsPerIpRule.holdsTrue(loanRequest)).thenReturn(false);
        when(maxLoanAmountRule.holdsTrue(loanRequest)).thenReturn(true);
        when(loanRequestSubmissionTimeRule.holdsTrue(loanRequest)).thenReturn(false);
        assertFalse(loanExpert.riskSurroundsLoan(loanRequest));
    }

    @Test
    public void testRiskSurroundsLoanIsFalseIfLessThanMaxLoanAmountAndRiskSubmissionTime() {
        when(maxLoanAttemptsPerIpRule.holdsTrue(loanRequest)).thenReturn(false);
        when(maxLoanAmountRule.holdsTrue(loanRequest)).thenReturn(false);
        when(loanRequestSubmissionTimeRule.holdsTrue(loanRequest)).thenReturn(true);
        assertFalse(loanExpert.riskSurroundsLoan(loanRequest));
    }
}

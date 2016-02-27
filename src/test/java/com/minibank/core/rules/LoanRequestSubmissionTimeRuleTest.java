package com.minibank.core.rules;

import com.minibank.InjectMocksTest;
import com.minibank.common.DateTimeParameters;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.BankParametersRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

public class LoanRequestSubmissionTimeRuleTest extends InjectMocksTest {

    private static final LocalTime RISK_TIME_START_1 = BankParametersFixture.STANDARD_RISK_TIME_START;
    private static final LocalTime RISK_TIME_END_1 = BankParametersFixture.STANDARD_RISK_TIME_END;

    private static final LocalTime RISK_TIME_START_2 = BankParametersFixture.NEW_RISK_TIME_START;
    private static final LocalTime RISK_TIME_END_2 = BankParametersFixture.NEW_RISK_TIME_END;

    @InjectMocks
    private LoanRequestSubmissionTimeRule rule;

    @Mock
    private BankParametersRepository bankParametersRepository;

    @Mock
    private BankParameters bankParameters;

    @Before
    public void setUp() {
        when(bankParametersRepository.getCurrentBankParameters()).thenReturn(bankParameters);
    }

    @Test
    public void testHoldsTrueIsTrueIfRiskTimeIntervalSpansOneDayAndSubmissionTimeIsInRiskInterval() {
        when(bankParameters.getRiskTimeStart()).thenReturn(RISK_TIME_START_1);
        when(bankParameters.getRiskTimeEnd()).thenReturn(RISK_TIME_END_1);

        LocalTime submissionTime = RISK_TIME_START_1.plusMinutes(1);
        LoanRequest loanRequest = createLoanRequest(submissionTime);
        assertTrue(rule.holdsTrue(loanRequest));

        submissionTime = RISK_TIME_END_1.minusMinutes(1);
        loanRequest = createLoanRequest(submissionTime);
        assertTrue(rule.holdsTrue(loanRequest));
    }

    @Test
    public void testHoldsTrueIsFalseIfRiskTimeIntervalSpansOneDayAndSubmissionTimeIsNotInRiskInterval() {
        when(bankParameters.getRiskTimeStart()).thenReturn(RISK_TIME_START_1);
        when(bankParameters.getRiskTimeEnd()).thenReturn(RISK_TIME_END_1);

        LocalTime submissionTime = RISK_TIME_START_1.minusMinutes(1);
        LoanRequest loanRequest = createLoanRequest(submissionTime);
        assertFalse(rule.holdsTrue(loanRequest));

        submissionTime = RISK_TIME_END_1.plusMinutes(1);
        loanRequest = createLoanRequest(submissionTime);
        assertFalse(rule.holdsTrue(loanRequest));
    }

    @Test
    public void testHoldsTrueIsTrueIfRiskTimeIntervalSpansTwoDaysAndSubmissionTimeIsInRiskInterval() {
        when(bankParameters.getRiskTimeStart()).thenReturn(RISK_TIME_START_2);
        when(bankParameters.getRiskTimeEnd()).thenReturn(RISK_TIME_END_2);

        LocalTime submissionTime = RISK_TIME_START_2.plusMinutes(1);
        LoanRequest loanRequest = createLoanRequest(submissionTime);
        assertTrue(rule.holdsTrue(loanRequest));

        submissionTime = RISK_TIME_END_2.minusMinutes(1);
        loanRequest = createLoanRequest(submissionTime);
        assertTrue(rule.holdsTrue(loanRequest));
    }

    @Test
    public void testHoldsFalseIsTrueIfRiskTimeIntervalSpansTwoDaysAndSubmissionTimeIsNotInRiskInterval() {
        when(bankParameters.getRiskTimeStart()).thenReturn(RISK_TIME_START_2);
        when(bankParameters.getRiskTimeEnd()).thenReturn(RISK_TIME_END_2);

        LocalTime submissionTime = RISK_TIME_START_2.minusMinutes(1);
        LoanRequest loanRequest = createLoanRequest(submissionTime);
        assertFalse(rule.holdsTrue(loanRequest));

        submissionTime = RISK_TIME_END_2.plusMinutes(1);
        loanRequest = createLoanRequest(submissionTime);
        assertFalse(rule.holdsTrue(loanRequest));
    }

    @Test
    public void testHoldsTrueIsTrueIfRiskTimeIntervalSpansTwoDaysAndSubmissionTimeEqualsToMaxTime() {
        when(bankParameters.getRiskTimeStart()).thenReturn(RISK_TIME_START_2);
        when(bankParameters.getRiskTimeEnd()).thenReturn(RISK_TIME_END_2);
        LocalTime submissionTime = DateTimeParameters.MAX_TIME;
        LoanRequest loanRequest = createLoanRequest(submissionTime);
        assertTrue(rule.holdsTrue(loanRequest));
    }

    private LoanRequest createLoanRequest(LocalTime submissionTime) {
        LoanRequest loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setSubmissionTime(submissionTime);
        return loanRequest;
    }
}
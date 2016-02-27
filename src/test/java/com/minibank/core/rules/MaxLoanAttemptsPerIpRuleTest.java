package com.minibank.core.rules;

import com.minibank.InjectMocksTest;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.BankParametersRepository;
import com.minibank.core.repositories.LoanRequestRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

public class MaxLoanAttemptsPerIpRuleTest extends InjectMocksTest {

    private static final byte MAX_LOAN_ATTEMPTS = BankParametersFixture.STANDARD_MAX_LOAN_ATTEMPTS;

    @InjectMocks
    private MaxLoanAttemptsPerIpRule rule;

    @Mock
    private BankParametersRepository bankParametersRepository;

    @Mock
    private LoanRequestRepository loanRequestRepository;

    @Mock
    private BankParameters bankParameters;

    @Mock
    private LoanRequest loanRequest;


    @Before
    public void setUp() {
        when(bankParametersRepository.getCurrentBankParameters()).thenReturn(bankParameters);
        when(bankParameters.getMaxLoanAttempts()).thenReturn(MAX_LOAN_ATTEMPTS);
    }

    @Test
    public void testHoldsTrueIsTrueIfMoreThanMaxLoanAttempts() {
        List<LoanRequest> loanRequests = createLoanRequests(MAX_LOAN_ATTEMPTS + 1);
        when(loanRequestRepository.getByRequestIp(anyString())).thenReturn(loanRequests);
        assertTrue(rule.holdsTrue(loanRequest));
    }

    @Test
    public void testHoldsTrueIsFalseIfMaxLoanAttempts() {
        List<LoanRequest> loanRequests = createLoanRequests(MAX_LOAN_ATTEMPTS);
        when(loanRequestRepository.getByRequestIp(anyString())).thenReturn(loanRequests);
        assertFalse(rule.holdsTrue(loanRequest));
    }

    @Test
    public void testHoldsTrueIsFalseIfLessThanMaxLoanAttempts() {
        List<LoanRequest> loanRequests = createLoanRequests(MAX_LOAN_ATTEMPTS - 1);
        when(loanRequestRepository.getByRequestIp(anyString())).thenReturn(loanRequests);
        assertFalse(rule.holdsTrue(loanRequest));
    }

    List<LoanRequest> createLoanRequests(int numberOfRequests) {
        List<LoanRequest> loanRequests = new ArrayList<>();
        LocalDate now = LocalDate.now();
        for(int i = 1; i <= numberOfRequests; i++) {
            LoanRequest loanRequest = LoanRequestFixture.standardLoanRequest();
            loanRequest.setSubmissionDate(now);
            loanRequests.add(loanRequest);
        }
        return loanRequests;
    }
}

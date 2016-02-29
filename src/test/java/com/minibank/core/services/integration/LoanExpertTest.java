package com.minibank.core.services.integration;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.Customer;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.LoanRequestRepository;
import com.minibank.core.services.LoanExpert;
import com.minibank.testutil.repositories.DatabaseCleaner;
import com.minibank.testutil.repositories.TestBankParametersRepository;
import com.minibank.testutil.repositories.TestCustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class LoanExpertTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    @Autowired
    private TestBankParametersRepository testBankParametersRepository;

    @Autowired
    private LoanExpert loanExpert;

    private BankParameters bankParameters;

    @Before
    public void setUp() {
        databaseCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        testBankParametersRepository.create(bankParameters);
    }

    @Test
    public void tesRiskSurroundsLoanIsTrueIfMaxLoanAmountAndRiskSubmissionTime(){
        BigDecimal maxLoanAmount = bankParameters.getMaxLoanAmount();
        LocalTime submissionTime = bankParameters.getRiskTimeStart().plusHours(1);
        LoanRequest loanRequest = createLoanRequest();
        loanRequest.setAmount(maxLoanAmount);
        loanRequest.setSubmissionTime(submissionTime);
        loanRequestRepository.create(loanRequest);
        assertTrue(loanExpert.riskSurroundsLoan(loanRequest));
    }

    @Test
    public void testRiskSurroundsLoanIsTrueIfMoreThanMaxLoanAttemptsPerIpDuringOneDay() {
        persistLoanRequests(bankParameters.getMaxLoanAttempts() + 1);
        LoanRequest loanRequest = createLoanRequest();
        assertTrue(loanExpert.riskSurroundsLoan(loanRequest));
    }

    @Test
    public void testRiskSurroundsLoanIsFalseIfMaxLoanAttemptsPerIpDuringOneDay() {
        persistLoanRequests(bankParameters.getMaxLoanAttempts());
        LoanRequest loanRequest = createLoanRequest();
        assertFalse(loanExpert.riskSurroundsLoan(loanRequest));
    }

    @Test
    public void testRiskSurroundsLoanIsFalseIfLessThanMaxLoanAmountAndNonRiskSubmissionTime() {
        LoanRequest loanRequest = createLoanRequest();
        loanRequestRepository.create(loanRequest);
        assertFalse(loanExpert.riskSurroundsLoan(loanRequest));
    }

    @Test
    public void testRiskSurroundsLoanIsFalseIfMaxLoanAmountAndNonRiskSubmissionTime() {
        LoanRequest loanRequest = createLoanRequest();
        loanRequest.setAmount(bankParameters.getMaxLoanAmount());
        assertFalse(loanExpert.riskSurroundsLoan(loanRequest));
    }

    @Test
    public void testRiskSurroundsLoanIsFalseIfLessThanMaxLoanAmountAndRiskSubmissionTime() {
        LoanRequest loanRequest = createLoanRequest();
        LocalTime submissionTime = bankParameters.getRiskTimeStart().plusHours(1);
        loanRequest.setSubmissionTime(submissionTime);
        assertFalse(loanExpert.riskSurroundsLoan(loanRequest));
    }

    private LoanRequest createLoanRequest() {
        Customer customer = CustomerFixture.standardCustomer();
        LoanRequest loanRequest = LoanRequestFixture.standardLoanRequest();
        testCustomerRepository.create(customer);
        loanRequest.setCustomer(customer);
        return loanRequest;
    }

    private void persistLoanRequests(int numberOfRequests) {
        LocalDate now = LocalDate.now();
        for (int i = 1; i <= numberOfRequests; i++) {
            LoanRequest loanRequest = createLoanRequest();
            loanRequest.setSubmissionDate(now);
            loanRequestRepository.create(loanRequest);
        }
    }

}

package com.minibank.core.services.helpers;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.Customer;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.BankParametersRepository;
import com.minibank.core.repositories.LoanRequestRepository;
import com.minibank.core.repositories.TestCustomerRepository;
import com.minibank.core.repositories.helpers.DBCleaner;
import com.minibank.core.services.RiskConstraintChecker;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class RiskConstraintCheckerTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    @Autowired
    private BankParametersRepository bankParametersRepository;

    @Autowired
    private RiskConstraintChecker checker;

    private BankParameters bankParameters;
    private LoanRequest loanRequest;


    @Before
    public void setUp() {
        dbCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        bankParametersRepository.create(bankParameters);
        createLoanRequest(LoanRequestFixture.SUBMISSION_DATE);
    }

    @Test
    public void testIsMaxRequestsPerIPExceeded() {
        bankParameters.setMaxLoanAttempts(new Byte("2"));
        bankParametersRepository.create(bankParameters);

        LocalDate now = LocalDate.now();

        createLoanRequest(now);
        assertFalse(checker.isMaxRequestsPerIpExceeded(loanRequest));
        createLoanRequest(now);
        assertFalse(checker.isMaxRequestsPerIpExceeded(loanRequest));
        createLoanRequest(now);
        assertTrue(checker.isMaxRequestsPerIpExceeded(loanRequest));
    }

    @Test
    public void testIsRiskTimeIfRiskTimeStartIsLessThanRiskTimeEnd() {
        //check loan requests for risk interval [00:00:00-07:00:00]

        LocalTime riskTimeStart = bankParameters.getRiskTimeStart();
        LocalTime riskTimeEnd = bankParameters.getRiskTimeEnd();

        //loan request in risk interval
        LocalTime submissionTime = riskTimeStart.plusHours(1);
        loanRequest.setSubmissionTime(submissionTime);
        assertTrue(checker.isRiskTime(loanRequest));

        submissionTime = riskTimeEnd.minusHours(1);
        loanRequest.setSubmissionTime(submissionTime);
        assertTrue(checker.isRiskTime(loanRequest));

        //loan request not in risk interval
        submissionTime = riskTimeStart.minusHours(1);
        loanRequest.setSubmissionTime(submissionTime);
        assertFalse(checker.isRiskTime(loanRequest));

        submissionTime = riskTimeEnd.plusHours(1);
        loanRequest.setSubmissionTime(submissionTime);
        assertFalse(checker.isRiskTime(loanRequest));
    }

    @Test
    public void testIsRiskTimeIfRiskTimeStartIsGraterThanRiskTimeEnd() {
        //check loan requests for risk interval [22:00:00-08:00:00]

        BankParameters newBankParams = BankParametersFixture.newBankParameters();
        bankParametersRepository.create(newBankParams);

        LocalTime riskTimeStart = newBankParams.getRiskTimeStart();
        LocalTime riskTimeEnd = newBankParams.getRiskTimeEnd();

        //loan request in risk interval
        LocalTime submissionTime = riskTimeStart.plusHours(1);
        loanRequest.setSubmissionTime(submissionTime);
        assertTrue(checker.isRiskTime(loanRequest));

        //loan request not in risk interval
        submissionTime = riskTimeEnd.plusHours(1);
        loanRequest.setSubmissionTime(submissionTime);
        assertFalse(checker.isRiskTime(loanRequest));

        submissionTime = riskTimeStart.minusHours(1);
        loanRequest.setSubmissionTime(submissionTime);
        assertFalse(checker.isRiskTime(loanRequest));
    }

    @Test
    public void testIsMaxAmount() {
        BigDecimal maxLoanAmount = bankParameters.getMaxLoanAmount();
        loanRequest.setAmount(maxLoanAmount);

        assertTrue(checker.isMaxAmount(loanRequest));

        BigDecimal loanAmount = maxLoanAmount.subtract(new BigDecimal(1.00));
        loanRequest.setAmount(loanAmount);

        assertTrue(!checker.isMaxAmount(loanRequest));

        loanAmount = maxLoanAmount.add(new BigDecimal(1.00));
        loanRequest.setAmount(loanAmount);

        assertTrue(!checker.isMaxAmount(loanRequest));
    }

    private void createLoanRequest(LocalDate submissionDate) {
        Customer customer = CustomerFixture.standardCustomer();
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setSubmissionDate(submissionDate);
        testCustomerRepository.create(customer);
        loanRequest.setCustomer(customer);
        loanRequestRepository.create(loanRequest);
    }
}

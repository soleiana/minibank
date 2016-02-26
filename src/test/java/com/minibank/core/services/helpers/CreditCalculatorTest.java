package com.minibank.core.services.helpers;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.fixtures.LoanFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.BankParametersRepository;
import com.minibank.core.repositories.helpers.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;


public class CreditCalculatorTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private BankParametersRepository bankParametersRepository;

    @Autowired
    private CreditCalculator creditCalculator;

    private BankParameters bankParameters;
    private LoanRequest loanRequest;
    private Loan loan;

    @Before
    public void setUp() {
        dbCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        bankParametersRepository.create(bankParameters);
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loan = LoanFixture.standardLoan();
    }

    @Test
    public void testGetLoanEndDate() {
        Date submissionDate = Date.valueOf("2014-09-01");
        loanRequest.setSubmissionDate(submissionDate);
        loanRequest.setTerm(20);

        Date endDate = creditCalculator.getLoanEndDate(loanRequest);
        assertEquals(Date.valueOf("2014-09-21"),endDate);
    }

    @Test
    public void testGetInterest_1() {
        BigDecimal amount = new BigDecimal("200.00");
        Integer term = 20;
        BigDecimal t = new BigDecimal("20");
        BigDecimal interestRate = bankParameters.getBaseInterestRate();
        BigDecimal expectedInterest = amount.multiply(interestRate)
                                            .multiply(t)
                                            .multiply(creditCalculator.FACTOR);
        expectedInterest = expectedInterest.setScale(2, RoundingMode.HALF_EVEN);
        loanRequest.setAmount(amount);
        loanRequest.setTerm(term);

        BigDecimal interest = creditCalculator.getInterest(loanRequest);
        assertTrue(interest.compareTo(expectedInterest) == 0);
    }

    @Test
    public void testGetInterest_2() {
        BigDecimal amount = new BigDecimal("200.00");
        Integer term = 20;
        BigDecimal interestRate = new BigDecimal("100.00");
        loan.setAmount(amount);
        loan.setTerm(term);
        loan.setCurrInterestRate(interestRate);

        BigDecimal interest = creditCalculator.getInterest(loan);
        assertTrue(interest.compareTo(new BigDecimal("22.50")) == 0);
    }

    @Test
    public void testGetLoanExtensionEndDate() {
        Date loanEndDate = Date.valueOf("2014-09-01");
        loan.setEndDate(loanEndDate);

        Date loanExtensionEndDate = creditCalculator.getLoanExtensionEndDate(loan);
        assertEquals(Date.valueOf("2014-09-08"),loanExtensionEndDate);
    }
}

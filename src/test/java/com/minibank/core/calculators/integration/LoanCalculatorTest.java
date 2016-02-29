package com.minibank.core.calculators.integration;

import com.minibank.SpringContextTest;
import com.minibank.core.calculators.LoanCalculator;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import com.minibank.testutil.repositories.DatabaseCleaner;
import com.minibank.testutil.repositories.TestBankParametersRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class LoanCalculatorTest extends SpringContextTest {

    private static final BigDecimal FACTOR = new BigDecimal("0.0000277777");

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private TestBankParametersRepository testBankParametersRepository;

    @Autowired
    private LoanCalculator loanCalculator;

    private BankParameters bankParameters;
    private LoanRequest loanRequest;


    @Before
    public void setUp() {
        databaseCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        testBankParametersRepository.create(bankParameters);
        loanRequest = LoanRequestFixture.standardLoanRequest();
    }

    @Test
    public void testGetLoanEndDate() {
        LocalDate submissionDate = LocalDate.of(2014, 9, 1);
        loanRequest.setSubmissionDate(submissionDate);
        loanRequest.setTerm(20);
        LocalDate endDate = loanCalculator.getLoanEndDate(loanRequest);
        assertEquals(LocalDate.of(2014, 9, 21), endDate);
    }

    @Test
    public void testGetInterest() {
        BigDecimal amount = new BigDecimal("200.00");
        Integer term = 20;
        BigDecimal t = new BigDecimal(term);
        BigDecimal interestRate = new BigDecimal("100.00");
        BigDecimal expectedInterest = amount.multiply(interestRate)
                                            .multiply(t)
                                            .multiply(FACTOR)
                                            .setScale(2, RoundingMode.HALF_EVEN);
        loanRequest.setAmount(amount);
        loanRequest.setTerm(term);

        BigDecimal interest = loanCalculator.getInterest(loanRequest);
        assertTrue(interest.compareTo(expectedInterest) == 0);
    }
}

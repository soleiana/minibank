package com.minibank.core.calculators.integration;

import com.minibank.SpringContextTest;
import com.minibank.core.calculators.LoanExtensionCalculator;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.fixtures.LoanFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.Loan;
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

public class LoanExtensionCalculatorTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private TestBankParametersRepository testBankParametersRepository;

    @Autowired
    private LoanExtensionCalculator loanExtensionCalculator;

    private BankParameters bankParameters;
    private Loan loan;

    @Before
    public void setUp() {
        databaseCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
        testBankParametersRepository.create(bankParameters);
        loan = LoanFixture.standardLoan();
    }

    @Test
    public void testGetLoanExtensionEndDate() {
        LocalDate loanEndDate = LocalDate.of(2014, 9, 1);
        loan.setEndDate(loanEndDate);
        LocalDate loanExtensionEndDate = loanExtensionCalculator.getLoanExtensionEndDate(loan);
        assertEquals(LocalDate.of(2014, 9, 8), loanExtensionEndDate);
    }

    @Test
    public void testGetInterest() {
        BigDecimal amount = new BigDecimal("200.00");
        Integer term = 20;
        BigDecimal interestRate = new BigDecimal("100.00");
        loan.setAmount(amount);
        loan.setTerm(term);
        loan.setCurrentInterestRate(interestRate);
        BigDecimal interest = loanExtensionCalculator.getInterest(loan);
        assertTrue(interest.compareTo(new BigDecimal("22.50")) == 0);
    }

    @Test
    public void testGetNewInterestRate() {
        BigDecimal interestRate = new BigDecimal("100.00");
        BigDecimal expectedInterestRate = interestRate
                .multiply(bankParameters.getInterestRateFactor())
                .setScale(2, RoundingMode.HALF_EVEN);
        BigDecimal newInterestRate = loanExtensionCalculator.getNewInterestRate(interestRate);
        assertTrue(newInterestRate.compareTo(expectedInterestRate) == 0);
    }

}

package com.minibank.core.calculators.unit;

import com.minibank.core.calculators.CalculationUtility;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.junit.Assert.assertTrue;


public class CalculationUtilityTest {

    private static final BigDecimal FACTOR = new BigDecimal("0.0000277777");

    private CalculationUtility calculationUtility;

    @Before
    public void setUp() {
        calculationUtility = new CalculationUtility();
    }

    @Test
    public void testInterestFormula() {
        BigDecimal amount = new BigDecimal("200.00");
        BigDecimal term = new BigDecimal("20");
        BigDecimal interestRate = new BigDecimal("100.00");
        BigDecimal expectedInterest = amount.multiply(interestRate)
                .multiply(term)
                .multiply(FACTOR)
                .setScale(2, RoundingMode.HALF_EVEN);

        BigDecimal interest = calculationUtility.interestFormula(amount, term, interestRate);
        assertTrue(interest.compareTo(expectedInterest) == 0);
    }

    @Test
    public void testInterestFormulaWithZeroTerm() {
        BigDecimal amount = new BigDecimal("200.00");
        BigDecimal term = new BigDecimal("0");
        BigDecimal interestRate = new BigDecimal("100.00");
        BigDecimal interest = calculationUtility.interestFormula(amount, term, interestRate);
        assertTrue(interest.compareTo(new BigDecimal(0)) == 0);
    }

    @Test
    public void testInterestFormulaWithZeroAmount() {
        BigDecimal amount = new BigDecimal("0.00");
        BigDecimal term = new BigDecimal("20");
        BigDecimal interestRate = new BigDecimal("100.00");
        BigDecimal interest = calculationUtility.interestFormula(amount, term, interestRate);
        assertTrue(interest.compareTo(new BigDecimal(0)) == 0);
    }
}

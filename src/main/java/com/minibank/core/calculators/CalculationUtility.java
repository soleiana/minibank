package com.minibank.core.calculators;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Component
public class CalculationUtility {

    private static final BigDecimal FACTOR = new BigDecimal("0.0000277777");


    public BigDecimal interestFormula(BigDecimal amount, BigDecimal term, BigDecimal interestRate) {
        BigDecimal factor = amount.multiply(interestRate).multiply(term);
        BigDecimal interest = factor.multiply(FACTOR);
        return interest.setScale(2, RoundingMode.HALF_EVEN);
    }

}

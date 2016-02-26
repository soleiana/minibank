package com.minibank.core.fixtures;

import com.minibank.core.model.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;


public class LoanFixture {

    public static final BigDecimal NEW_CURRENT_INTEREST_RATE = new BigDecimal("150.00");
    public static final BigDecimal NEW_CURRENT_INTEREST = new BigDecimal("300.00");
    public static final LocalDate NEW_END_DATE = LocalDate.of(2014, 10, 6);

    private static final Integer STANDARD_TERM = 30;
    private static final BigDecimal STANDARD_AMOUNT = new BigDecimal("200.00");
    private static final BigDecimal STANDARD_CURRENT_INTEREST_RATE = new BigDecimal("100.00");
    private static final BigDecimal STANDARD_CURRENT_INTEREST = new BigDecimal("200.00");
    private static final LocalDate START_DATE = LocalDate.of(2014, 9, 1);
    private static final LocalDate STANDARD_END_DATE = LocalDate.of(2014, 9, 30);

    public static Loan standardLoan() {
        Loan loan = new Loan();
        loan.setAmount(STANDARD_AMOUNT);
        loan.setTerm(STANDARD_TERM);
        loan.setCurrInterest(STANDARD_CURRENT_INTEREST);
        loan.setCurrInterestRate(STANDARD_CURRENT_INTEREST_RATE);
        loan.setStartDate(START_DATE);
        loan.setEndDate(STANDARD_END_DATE);
        return loan;
    }
}

package com.minibank.communications.fixtures;

import com.minibank.communications.model.Loan;

import java.math.BigDecimal;
import java.time.LocalDate;


public class LoanFixture {

    private static final BigDecimal STANDARD_CURRENT_INTEREST_RATE = new BigDecimal("100.00");
    private static final BigDecimal STANDARD_CURRENT_INTEREST = new BigDecimal("200.00");
    private static final LocalDate START_DATE = LocalDate.of(2014, 9, 1);
    private static final LocalDate STANDARD_END_DATE = LocalDate.of(2014, 9, 30);
    private static final BigDecimal AMOUNT = new BigDecimal("500.00");
    private static final Integer LOAN_ID = 1;

    public static Loan standardLoan() {
        Loan loan = new Loan();
        loan.setCurrInterest(STANDARD_CURRENT_INTEREST);
        loan.setCurrInterestRate(STANDARD_CURRENT_INTEREST_RATE);
        loan.setStartDate(START_DATE);
        loan.setEndDate(STANDARD_END_DATE);
        loan.setId(LOAN_ID);
        loan.setAmount(AMOUNT);
        return loan;
    }
}

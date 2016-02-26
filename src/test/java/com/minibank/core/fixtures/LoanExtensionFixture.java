package com.minibank.core.fixtures;

import com.minibank.core.model.LoanExtension;

import java.math.BigDecimal;
import java.time.LocalDate;


public class LoanExtensionFixture {

    private static final LocalDate START_DATE = LocalDate.of(2014, 9, 30);
    private static final LocalDate END_DATE = LocalDate.of(2014, 10, 6);
    private static final BigDecimal INTEREST_RATE =  new BigDecimal("150.00");
    private static final BigDecimal INTEREST =  new BigDecimal("300.00");
    private static final LocalDate SUBMISSION_DATE = LocalDate.of(2014, 9, 29);

    public static LoanExtension standardLoanExtension() {
        LoanExtension loanExtension = new LoanExtension();
        loanExtension.setStartDate(START_DATE);
        loanExtension.setEndDate(END_DATE);
        loanExtension.setInterestRate(INTEREST_RATE);
        loanExtension.setInterest(INTEREST);
        loanExtension.setSubmissionDate(SUBMISSION_DATE);
        return loanExtension;
    }
}

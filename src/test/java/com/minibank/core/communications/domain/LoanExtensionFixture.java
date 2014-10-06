package com.minibank.core.communications.domain;

import com.minibank.core.communications.loans.domain.LoanExtension;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Ann on 02/10/14.
 */
public class LoanExtensionFixture
{
    private static final Date START_DATE = Date.valueOf("2014-09-30");
    private static final Date END_DATE = Date.valueOf("2014-10-06");
    private static final BigDecimal INTEREST_RATE =  new BigDecimal("150.00");
    private static final BigDecimal INTEREST =  new BigDecimal("300.00");
    private static final Date SUBMISSION_DATE = Date.valueOf("2014-09-29");
    private static final Integer LOAN_ID = 1;

    public static LoanExtension standardLoanExtension()
    {
        LoanExtension loanExtension = new LoanExtension();
        loanExtension.setStartDate(START_DATE);
        loanExtension.setEndDate(END_DATE);
        loanExtension.setInterestRate(INTEREST_RATE);
        loanExtension.setInterest(INTEREST);
        loanExtension.setSubmissionDate(SUBMISSION_DATE);
        loanExtension.setLoanId(LOAN_ID);

        return loanExtension;
    }
}
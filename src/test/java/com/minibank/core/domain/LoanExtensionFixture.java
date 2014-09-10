package com.minibank.core.domain;

import java.math.BigDecimal;
import  java.sql.Date;

/**
 * Created by Ann on 10/09/14.
 */
public class LoanExtensionFixture
{

    public static final Date START_DATE = Date.valueOf("2014-09-30");
    public static final Date END_DATE = Date.valueOf("2014-10-06");
    public static final BigDecimal INTEREST_RATE =  new BigDecimal("150.00");
    public static final BigDecimal INTEREST =  new BigDecimal("300.00");
    public static final Date SUBMISSION_DATE = Date.valueOf("2014-09-29");

    public static LoanExtension standardLoanExtension()
    {
        LoanExtension loanExtension = new LoanExtension();
        loanExtension.setStartDate(START_DATE);
        loanExtension.setEndDate(END_DATE);
        loanExtension.setInterestRate(INTEREST_RATE);
        loanExtension.setInterest(INTEREST);
        loanExtension.setSubmissionDate(SUBMISSION_DATE);
        return loanExtension;
    }


}

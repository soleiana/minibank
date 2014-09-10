package com.minibank.core.domain;

import java.math.BigDecimal;
import  java.sql.Date;

/**
 * Created by Ann on 10/09/14.
 */
public class LoanExtensionFixture
{

    public static final Date START_DATE = Date.valueOf("2014-09-10");
    public static final Date END_DATE = Date.valueOf("2014-09-17");
    public static final BigDecimal INTEREST_RATE =  new BigDecimal("150.00");
    public static final BigDecimal INTEREST =  new BigDecimal("300.00");
    public static final Date SUBMISSION_DATE = Date.valueOf("2014-09-09");

    public static LoanExtension standardLoanExtension()
    {
        LoanExtension loanExtension = new LoanExtension();
        //Todo: set properties
        return loanExtension;
    }


}

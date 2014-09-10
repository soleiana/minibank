package com.minibank.core.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

/**
 * Created by Ann on 10/09/14.
 */
public class LoanRequestFixture
{
    public static final Date SUBMISSION_DATE = Date.valueOf("2014-09-01");
    public static final Time SUBMISSION_TIME = Time.valueOf("14:00:00");
    public static final Integer TERM = new Integer(30);
    public static final BigDecimal AMOUNT = new BigDecimal("200.00");
    public static final LoanRequestStatus STANDARD_STATUS = LoanRequestStatus.NEW;
    public static final LoanRequestStatus NEW_STATUS = LoanRequestStatus.APPROVED;

    public static LoanRequest standardLoanRequest()
    {
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setSubmissionDate(SUBMISSION_DATE);
        loanRequest.setSubmissionTime(SUBMISSION_TIME);
        loanRequest.setTerm(TERM);
        loanRequest.setAmount(AMOUNT);
        loanRequest.setStatus(STANDARD_STATUS);
        return  loanRequest;
    }
}

package com.minibank.core.domain;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;


public class LoanRequestFixture {

    public static final Date SUBMISSION_DATE = Date.valueOf("2014-09-01");

    private static final Time SUBMISSION_TIME = Time.valueOf("14:00:00");
    private static final Integer TERM = 30;
    private static final BigDecimal AMOUNT = new BigDecimal("200.00");
    private static final String REQUEST_IP = "127.0.0.1";

    public static LoanRequest standardLoanRequest() {
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setSubmissionDate(SUBMISSION_DATE);
        loanRequest.setSubmissionTime(SUBMISSION_TIME);
        loanRequest.setTerm(TERM);
        loanRequest.setAmount(AMOUNT);
        loanRequest.setRequestIp(REQUEST_IP);
        return  loanRequest;
    }
}

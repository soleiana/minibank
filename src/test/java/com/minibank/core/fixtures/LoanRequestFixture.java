package com.minibank.core.fixtures;

import com.minibank.core.model.LoanRequest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;


public class LoanRequestFixture {

    private static final LocalDate SUBMISSION_DATE = LocalDate.of(2014, 9, 1);
    private static final LocalTime SUBMISSION_TIME = LocalTime.of(14, 0, 0);
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
        return loanRequest;
    }
}

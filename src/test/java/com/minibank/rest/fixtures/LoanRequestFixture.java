package com.minibank.rest.fixtures;

import com.minibank.rest.model.LoanRequest;

import java.math.BigDecimal;


public class LoanRequestFixture {

    private static final Integer TERM = 30;
    private static final BigDecimal AMOUNT = new BigDecimal("200.00");

    public static LoanRequest standardLoanRequest(int customerId) {
        LoanRequest loanRequest = new LoanRequest();
        loanRequest.setCustomerId(customerId);
        loanRequest.setTerm(TERM);
        loanRequest.setAmount(AMOUNT);
        return loanRequest;
    }
}

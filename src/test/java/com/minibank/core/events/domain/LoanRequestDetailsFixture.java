package com.minibank.core.events.domain;

import com.minibank.core.events.loans.domain.LoanRequestDetails;
import java.math.BigDecimal;

/**
 * Created by Ann on 14/09/14.
 */
public class LoanRequestDetailsFixture
{
    private static final String REQUEST_IP = "127.0.0.1";
    private static final Integer TERM = 20;
    private static final BigDecimal AMOUNT = new BigDecimal("200.00");

    public static LoanRequestDetails standardLoanRequestDetails()
    {
        LoanRequestDetails loanRequestDetails = new LoanRequestDetails();
        loanRequestDetails.setRequestIP(REQUEST_IP);
        loanRequestDetails.setTerm(TERM);
        loanRequestDetails.setAmount(AMOUNT);
        return loanRequestDetails;
    }
}

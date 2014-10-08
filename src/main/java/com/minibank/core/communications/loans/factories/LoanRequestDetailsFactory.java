package com.minibank.core.communications.loans.factories;

import com.minibank.core.communications.loans.domain.LoanRequestDetails;
import com.minibank.rest.domain.LoanRequest;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 16/09/14.
 */
@Component
public class LoanRequestDetailsFactory
{
    public LoanRequestDetails getNewLoanRequestDetails(LoanRequest loanRequest)
    {
        LoanRequestDetails loanRequestDetails = new LoanRequestDetails();
        loanRequestDetails.setCustomerId(loanRequest.getCustomerId());
        loanRequestDetails.setRequestIp(loanRequest.getRequestIp());
        loanRequestDetails.setAmount(loanRequest.getAmount());
        loanRequestDetails.setTerm(loanRequest.getTerm());

        return loanRequestDetails;
    }
}

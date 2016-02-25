package com.minibank.communications.factories;

import com.minibank.communications.domain.LoanRequestDetails;
import com.minibank.rest.domain.LoanRequest;
import org.springframework.stereotype.Component;


@Component
public class LoanRequestDetailsFactory {

    public LoanRequestDetails getLoanRequestDetails(LoanRequest loanRequest) {
        LoanRequestDetails loanRequestDetails = new LoanRequestDetails();
        loanRequestDetails.setCustomerId(loanRequest.getCustomerId());
        loanRequestDetails.setRequestIp(loanRequest.getRequestIp());
        loanRequestDetails.setAmount(loanRequest.getAmount());
        loanRequestDetails.setTerm(loanRequest.getTerm());
        return loanRequestDetails;
    }
}

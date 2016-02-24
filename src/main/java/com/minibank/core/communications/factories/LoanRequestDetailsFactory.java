package com.minibank.core.communications.factories;

import com.minibank.core.communications.domain.LoanRequestDetails;
import com.minibank.rest.domain.LoanRequest;
import org.springframework.stereotype.Component;


@Component
public class LoanRequestDetailsFactory {

    public LoanRequestDetails getNewLoanRequestDetails(LoanRequest loanRequest) {
        LoanRequestDetails loanRequestDetails = new LoanRequestDetails();
        loanRequestDetails.setCustomerId(loanRequest.getCustomerId());
        loanRequestDetails.setRequestIp(loanRequest.getRequestIp());
        loanRequestDetails.setAmount(loanRequest.getAmount());
        loanRequestDetails.setTerm(loanRequest.getTerm());
        return loanRequestDetails;
    }
}

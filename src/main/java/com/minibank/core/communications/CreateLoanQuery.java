package com.minibank.core.communications;


import com.minibank.core.communications.domain.LoanRequestDetails;


public class CreateLoanQuery extends CreateEntityQuery {

    private final LoanRequestDetails loanRequestDetails;

    public CreateLoanQuery(LoanRequestDetails loanRequestDetails) {
        this.loanRequestDetails = loanRequestDetails;
    }

    public LoanRequestDetails getLoanRequestDetails() {
        return loanRequestDetails;
    }

}

package com.minibank.communications;


import com.minibank.communications.model.LoanRequestDetails;


public class CreateLoanQuery extends CreateEntityQuery {

    private final LoanRequestDetails loanRequestDetails;

    public CreateLoanQuery(LoanRequestDetails loanRequestDetails) {
        this.loanRequestDetails = loanRequestDetails;
    }

    public LoanRequestDetails getLoanRequestDetails() {
        return loanRequestDetails;
    }

}

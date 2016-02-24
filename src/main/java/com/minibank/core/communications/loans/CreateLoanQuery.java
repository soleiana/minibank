package com.minibank.core.communications.loans;

import com.minibank.core.communications.CreateEntityQuery;
import com.minibank.core.communications.loans.domain.LoanRequestDetails;


public class CreateLoanQuery extends CreateEntityQuery {

    private final LoanRequestDetails loanRequestDetails;

    public CreateLoanQuery(LoanRequestDetails loanRequestDetails) {
        this.loanRequestDetails = loanRequestDetails;
    }

    public LoanRequestDetails getLoanRequestDetails() {
        return loanRequestDetails;
    }

}

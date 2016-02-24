package com.minibank.core.communications.loans;

import com.minibank.core.communications.CreateEntityQuery;


public class CreateLoanExtensionQuery extends CreateEntityQuery {

    private final Integer loanId;

    public CreateLoanExtensionQuery(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getLoanId() {
        return loanId;
    }
}

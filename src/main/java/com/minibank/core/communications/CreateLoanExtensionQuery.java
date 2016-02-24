package com.minibank.core.communications;


public class CreateLoanExtensionQuery extends CreateEntityQuery {

    private final Integer loanId;

    public CreateLoanExtensionQuery(Integer loanId) {
        this.loanId = loanId;
    }

    public Integer getLoanId() {
        return loanId;
    }
}

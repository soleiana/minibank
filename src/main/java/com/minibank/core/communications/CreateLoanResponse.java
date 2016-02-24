package com.minibank.core.communications;


public class CreateLoanResponse extends CreateEntityResponse {

    public CreateLoanResponse(Boolean loanCreated, String message) {
        super.created = loanCreated;
        super.message = message;
    }

}

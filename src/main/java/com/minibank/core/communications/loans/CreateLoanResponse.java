package com.minibank.core.communications.loans;

import com.minibank.core.communications.CreateEntityResponse;


public class CreateLoanResponse extends CreateEntityResponse {

    public CreateLoanResponse(Boolean loanCreated, String message) {
        super.created = loanCreated;
        super.message = message;
    }

}

package com.minibank.core.communications.loans;

import com.minibank.core.communications.CreateEntityResponse;


public class CreateLoanExtensionResponse extends CreateEntityResponse {

    public CreateLoanExtensionResponse(Boolean loanExtensionCreated, String message) {
        super.created = loanExtensionCreated;
        super.message = message;
    }
}

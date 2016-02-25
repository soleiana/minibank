package com.minibank.core.communications;


public class CreateLoanExtensionResponse extends CreateEntityResponse {

    public CreateLoanExtensionResponse(Boolean loanExtensionCreated, String message) {
        super(loanExtensionCreated, message);
    }
}

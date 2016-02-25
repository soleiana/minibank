package com.minibank.communications;


public class CreateLoanExtensionResponse extends CreateEntityResponse {

    public CreateLoanExtensionResponse(Boolean loanExtensionCreated, String message) {
        super(loanExtensionCreated, message);
    }
}

package com.minibank.communications;


public class CreateLoanResponse extends CreateEntityResponse {

    public CreateLoanResponse(Boolean loanCreated, String message) {
      super(loanCreated, message);
    }

}

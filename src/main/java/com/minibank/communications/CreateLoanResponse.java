package com.minibank.communications;


public class CreateLoanResponse extends CreateEntityResponse {

    public Integer loanId;

    public CreateLoanResponse(Boolean loanCreated, String message) {
      super(loanCreated, message);
    }

}

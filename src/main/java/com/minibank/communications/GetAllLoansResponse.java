package com.minibank.communications;


import com.minibank.communications.model.AllLoansDetails;


public class GetAllLoansResponse extends GetEntityResponse {

    private final AllLoansDetails allLoansDetails;

    public GetAllLoansResponse(AllLoansDetails allLoansDetails, boolean entityFound) {
        super(entityFound);
        this.allLoansDetails = allLoansDetails;
    }

    public GetAllLoansResponse(boolean isErrorResponse) {
        super(false, isErrorResponse);
        this.allLoansDetails = null;
    }

    public AllLoansDetails getAllLoansDetails() {
        return allLoansDetails;
    }
}

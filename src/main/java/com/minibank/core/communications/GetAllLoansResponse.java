package com.minibank.core.communications;


import com.minibank.core.communications.domain.AllLoansDetails;


public class GetAllLoansResponse extends GetEntityResponse {

    private final AllLoansDetails allLoansDetails;

    public GetAllLoansResponse(AllLoansDetails allLoansDetails, Boolean entityFound) {
        super(entityFound);
        this.allLoansDetails = allLoansDetails;
    }

    public AllLoansDetails getAllLoansDetails() {
        return allLoansDetails;
    }
}

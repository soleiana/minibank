package com.minibank.core.communications;


import com.minibank.core.communications.domain.AllLoansDetails;


public class GetAllLoansResponse extends GetEntityResponse {

    private final AllLoansDetails allLoansDetails;

    public GetAllLoansResponse(AllLoansDetails allLoansDetails, Boolean entityFound) {
        this.allLoansDetails = allLoansDetails;
        super.entityFound = entityFound;
    }

    public AllLoansDetails getAllLoansDetails() {
        return allLoansDetails;
    }
}

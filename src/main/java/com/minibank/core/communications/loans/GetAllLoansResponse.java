package com.minibank.core.communications.loans;


import com.minibank.core.communications.GetEntityResponse;
import com.minibank.core.communications.loans.domain.AllLoansDetails;


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

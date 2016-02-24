package com.minibank.core.communications.loans;


import com.minibank.core.communications.GetEntityQuery;


public class GetAllLoansQuery extends GetEntityQuery {

    private final Integer customerId;

    public GetAllLoansQuery(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }
}

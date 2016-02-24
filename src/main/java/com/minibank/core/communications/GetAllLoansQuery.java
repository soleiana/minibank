package com.minibank.core.communications;


public class GetAllLoansQuery extends GetEntityQuery {

    private final Integer customerId;

    public GetAllLoansQuery(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getCustomerId() {
        return customerId;
    }
}

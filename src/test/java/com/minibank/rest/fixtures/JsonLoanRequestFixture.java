package com.minibank.rest.fixtures;

public class JsonLoanRequestFixture {

    public static String standardLoanRequest() {
        return "{\"customerId\": 2, \"term\": 30, \"amount\" : 200.00}";
    }

    public static String loanRequestWithNullCustomerId() {
        return "{\"customerId\": null, \"term\": 30, \"amount\" : 200.00}";
    }

    public static String loanRequestWithNegativeCustomerId() {
        return "{\"customerId\": -1, \"term\": 30, \"amount\" : 200.00}";
    }

    public static String loanRequestWithTermLessThanMin() {
        return "{\"customerId\": 2, \"term\": 6, \"amount\" : 200.00}";
    }
    public static String loanRequestWithTermMoreThanMax() {
        return "{\"customerId\": 2, \"term\": 361, \"amount\" : 200.00}";
    }

    public static String loanRequestWithAmountLessThanMin() {
        return "{\"customerId\": 2, \"term\": 30, \"amount\" : 9.99}";
    }

    public static String loanRequestWithAmountMoreThanMax() {
        return "{\"customerId\": 2, \"term\": 30, \"amount\" : 10000.01}";
    }
}

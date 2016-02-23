package com.minibank.core.domain;

import java.util.List;


public class AllLoans {

    private Customer customer;

    private List<Loan> loans;

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public void setLoans(List<Loan> loans) {
        this.loans = loans;
    }
}

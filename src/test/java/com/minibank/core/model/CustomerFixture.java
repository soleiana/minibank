package com.minibank.core.model;


public class CustomerFixture {

    private static final String NAME = "James";
    private static final String SURNAME = "Bond";

    public static Customer standardCustomer() {
        Customer customer = new Customer();
        customer.setName(NAME);
        customer.setSurname(SURNAME);
        return  customer;
    }
}

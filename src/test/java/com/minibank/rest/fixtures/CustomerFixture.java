package com.minibank.rest.fixtures;

import com.minibank.rest.model.Customer;

public class CustomerFixture {

    private static final Integer ID = 2;
    private static final String NAME = "James";
    private static final String SURNAME = "Bond";

    public static Customer standardCustomer() {
        Customer customer = new Customer();
        customer.setId(ID);
        customer.setName(NAME);
        customer.setSurname(SURNAME);
        return customer;
    }
}

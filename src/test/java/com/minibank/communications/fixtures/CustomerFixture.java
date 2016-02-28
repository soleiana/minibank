package com.minibank.communications.fixtures;

import com.minibank.communications.model.Customer;

public class CustomerFixture {

    private static final Integer ID = 2;
    private static final String NAME = "James";
    private static final String SURNAME = "Bond";

    public static Customer standardCustomer() {
        Customer customer = new Customer();
        customer.setName(NAME);
        customer.setSurname(SURNAME);
        return customer;
    }
}

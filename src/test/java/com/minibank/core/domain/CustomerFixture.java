package com.minibank.core.domain;

/**
 * Created by Ann on 10/09/14.
 */
public class CustomerFixture
{
    public static final String NAME = "James";
    public static final String SURNAME = "Bond";

    public static Customer standardCustomer()
    {
        Customer customer = new Customer();
        customer.setName(NAME);
        customer.setSurname(SURNAME);
        return  customer;
    }
}

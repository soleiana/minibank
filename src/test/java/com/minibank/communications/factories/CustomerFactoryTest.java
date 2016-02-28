package com.minibank.communications.factories;

import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.model.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerFactoryTest  {

    private CustomerFactory customerFactory = new CustomerFactory();

    @Test
    public void testGetCustomer() {
        Customer customer = CustomerFixture.standardCustomer();
        com.minibank.communications.model.Customer resultCustomer = customerFactory.getCustomer(customer);
        assertEquals(customer.getId(), resultCustomer.getId());
        assertEquals(customer.getName(), resultCustomer.getName());
        assertEquals(customer.getSurname(), resultCustomer.getSurname());
    }
}
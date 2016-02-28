package com.minibank.rest.factories;

import com.minibank.communications.fixtures.CustomerFixture;
import com.minibank.communications.model.Customer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerRestFactoryTest {

    private CustomerRestFactory customerRestFactory = new CustomerRestFactory();

    @Test
    public void testGetCustomer()  {
        Customer customer = CustomerFixture.standardCustomer();
        com.minibank.rest.model.Customer resultCustomer = customerRestFactory.getCustomer(customer);
        assertEquals(customer.getId(), resultCustomer.getId());
        assertEquals(customer.getName(), resultCustomer.getName());
        assertEquals(customer.getSurname(), resultCustomer.getSurname());
    }
}
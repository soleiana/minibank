package com.minibank.testutil.services;

import com.minibank.SpringContextTest;
import com.minibank.core.model.Customer;
import com.minibank.testutil.repositories.DatabaseCleaner;
import com.minibank.testutil.repositories.TestCustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CustomerConfiguratorTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CustomerConfigurator customerConfigurator;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    @Before
    public void setUp() {
        databaseCleaner.clear();
    }

    @Test
    public void testPersistCustomer() {
        customerConfigurator.persistCustomer();
        Customer customer = testCustomerRepository.getLast();
        assertNotNull(customer);
        assertNotNull(customer.getId());
        assertEquals("James", customer.getName());
        assertEquals("Bond", customer.getSurname());
    }
}
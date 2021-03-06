package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.model.Customer;
import com.minibank.testutil.repositories.DatabaseCleaner;
import com.minibank.testutil.repositories.TestCustomerRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class CustomerRepositoryTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestCustomerRepository testCustomerRepository;


    @Before
    public void setUp() {
        databaseCleaner.clear();
    }

    @Test
    public void testCreate() {
        Customer customer =  CustomerFixture.standardCustomer();
        testCustomerRepository.create(customer);
        assertNotNull(customer.getId());
    }

    @Test
    public void testGetById() {
        Customer customer = CustomerFixture.standardCustomer();
        testCustomerRepository.create(customer);
        Integer id = customer.getId();
        assertEquals(customer, customerRepository.getById(id));
    }
}

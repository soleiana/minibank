package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.model.Customer;
import com.minibank.core.model.CustomerFixture;
import com.minibank.core.repositories.helpers.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static junit.framework.TestCase.assertEquals;


public class CustomerRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    private Customer customer;

    @Before
    public void setUp() {
        dbCleaner.clear();
        customer = CustomerFixture.standardCustomer();
    }

    @Test
    public void testGetById() {
        testCustomerRepository.create(customer);
        Integer id = customer.getId();
        assertEquals(customer, customerRepository.getById(id));
    }
}

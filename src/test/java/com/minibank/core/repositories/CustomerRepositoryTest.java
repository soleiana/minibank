package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.model.Customer;
import com.minibank.core.model.CustomerFixture;

import com.minibank.core.repositories.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;


public class CustomerRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @Before
    public void setUp() {
        dbCleaner.clear();
        customer = CustomerFixture.standardCustomer();
    }

    @Test
    @Transactional
    public void testCreate() {
        customerRepository.create(customer);
        assertNotNull(customer.getId());
    }

    @Test
    @Transactional
    public void testGetById() {
        customerRepository.create(customer);
        Integer id = customer.getId();
        assertEquals(customer, customerRepository.getById(id));
    }
}

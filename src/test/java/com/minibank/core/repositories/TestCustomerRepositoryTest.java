package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.model.Customer;
import com.minibank.core.model.CustomerFixture;
import com.minibank.core.repositories.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


import static org.junit.Assert.*;

public class TestCustomerRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    private Customer customer;

    @Before
    public void setUp() {
        dbCleaner.clear();
        customer = CustomerFixture.standardCustomer();
    }

    @Test
    public void testCreate() {
        testCustomerRepository.create(customer);
        assertNotNull(customer.getId());
    }
}
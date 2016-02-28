package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.model.Customer;
import com.minibank.core.repositories.helpers.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class TestCustomerRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    @Before
    public void setUp() {
        dbCleaner.clear();
    }

    @Test
    public void testCreate() {
        Customer customer =  CustomerFixture.standardCustomer();
        testCustomerRepository.create(customer);
        assertNotNull(customer.getId());
    }
}
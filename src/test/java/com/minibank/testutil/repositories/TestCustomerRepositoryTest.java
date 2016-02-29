package com.minibank.testutil.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.model.Customer;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class TestCustomerRepositoryTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

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

}
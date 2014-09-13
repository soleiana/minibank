package com.minibank.core.repository;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.Customer;
import com.minibank.core.domain.CustomerFixture;

import com.minibank.core.repository.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ann on 10/09/14.
 */
public class CustomerRepositoryImplTest extends SpringContextTest
{
    @Autowired
    private DBCleaner dbCleaner;
    @Autowired
    private CustomerRepository customerRepository;
    private Customer customer;

    @Before
    public void setUp() throws DBException
    {
        dbCleaner.clear();
        customer = CustomerFixture.standardCustomer();
    }
    @Test
    @Transactional
    public void testCreate() throws DBException
    {
        customerRepository.create(customer);
        assertNotNull(customer.getId());
    }

    @Test
    @Transactional
    public void testGetById() throws DBException
    {
        customerRepository.create(customer);
        Integer id = customer.getId();
        assertEquals(customer, customerRepository.getById(id));
    }
}

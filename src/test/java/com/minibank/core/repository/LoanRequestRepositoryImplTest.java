package com.minibank.core.repository;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.repository.tools.DBCleaner;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Ann on 10/09/14.
 */
public class LoanRequestRepositoryImplTest extends SpringContextTest
{
    @Autowired
    private DBCleaner dbCleaner;
    @Autowired
    private LoanRequestRepository loanRequestRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RequestIPRepository requestIPRepository;

    private LoanRequest loanRequest;
    private RequestIP requestIP;
    private Customer customer;

    @Before
    @Transactional
    public void setUp() throws DBException
    {
        requestIP = RequestIPFixture.standardRequestIP();
        customer = CustomerFixture.standardCustomer();



    }

    @After
    public void tearDown() throws  DBException
    {

    }

    @Test
    @Transactional
    public void testCreate() throws DBException
    {

    }

    @Test
    @Transactional
    public void testUpdate() throws DBException
    {

    }

    @Test
    @Transactional
    public void testGetById() throws DBException
    {

    }
}

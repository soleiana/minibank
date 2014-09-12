package com.minibank.core.repository;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.repository.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by Ann on 10/09/14.
 */
public class LoanRequestRepositoryImplTest extends SpringContextTest
{
    @Autowired
    @Qualifier("ORM")
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
        dbCleaner.clear();
        requestIP = RequestIPFixture.standardRequestIP();
        customer = CustomerFixture.standardCustomer();
        loanRequest = LoanRequestFixture.standardLoanRequest();

        requestIPRepository.create(requestIP);
        customerRepository.create(customer);
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIP(requestIP);
    }

    @Test
    @Transactional
    public void testCreate() throws DBException
    {
        loanRequestRepository.create(loanRequest);
        assertNotNull(loanRequest.getId());

    }

    @Test
    @Transactional
    public void testUpdate() throws DBException
    {
        loanRequestRepository.create(loanRequest);
        loanRequest.setStatus(LoanRequestFixture.NEW_STATUS);
        loanRequestRepository.update(loanRequest);
        assertEquals(LoanRequestFixture.NEW_STATUS, loanRequest.getStatus());
    }

    @Test
    @Transactional
    public void testGetById() throws DBException
    {
        loanRequestRepository.create(loanRequest);
        Integer id = loanRequest.getId();
        assertEquals(loanRequest, loanRequestRepository.getById(id));
    }
}

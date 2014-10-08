package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.repositories.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;

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

    private void createLoanRequest()
    {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIP(requestIP);
    }

    @Before
    @Transactional
    public void setUp()
    {
        dbCleaner.clear();
        requestIP = RequestIPFixture.standardRequestIP();
        requestIPRepository.create(requestIP);
        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);
        createLoanRequest();

    }

    @Test
    @Transactional
    public void testCreate()
    {
        loanRequestRepository.create(loanRequest);
        assertNotNull(loanRequest.getId());
    }

    @Test
    @Transactional
    public void testUpdate()
    {
        loanRequestRepository.create(loanRequest);
        loanRequest.setStatus(LoanRequestFixture.NEW_STATUS);
        loanRequestRepository.update(loanRequest);
        assertEquals(LoanRequestFixture.NEW_STATUS, loanRequest.getStatus());
    }

    @Test
    @Transactional
    public void testGetById()
    {
        loanRequestRepository.create(loanRequest);
        Integer id = loanRequest.getId();
        assertEquals(loanRequest, loanRequestRepository.getById(id));
    }

    @Test
    @Transactional
    public void testGetByRequestIP()
    {
        createLoanRequest();
        loanRequestRepository.create(loanRequest);
        createLoanRequest();
        loanRequestRepository.create(loanRequest);
        List<LoanRequest> loanRequests = loanRequestRepository.getByRequestIP(requestIP);
        assertEquals(2, loanRequests.size());
    }
}

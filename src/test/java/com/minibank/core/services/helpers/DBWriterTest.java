package com.minibank.core.services.helpers;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.repositories.CustomerRepository;
import com.minibank.core.repositories.DBException;
import com.minibank.core.repositories.RequestIPRepository;
import com.minibank.core.repositories.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertNull;

/**
 * Created by Ann on 13/09/14.
 */
public class DBWriterTest extends SpringContextTest
{
    @Autowired
    private DBCleaner dbCleaner;
    @Autowired
    private RequestIPRepository requestIPRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private DBWriter dbWriter;
    private RequestIP requestIP;
    private LoanRequest loanRequest;
    private Customer customer;

    @Before
    public void setUp() throws DBException
    {
        dbCleaner.clear();
        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);
    }

    private void createLoanRequest() throws DBException
    {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        requestIP = RequestIPFixture.standardRequestIP();
        loanRequest.setRequestIP(requestIP);
        loanRequest.setCustomer(customer);
    }

    @Test
    @Transactional
    public void testDBWriter_1() throws DBException
    {
        //scenario with new loan request with new ip address
        createLoanRequest();
        assertNull(loanRequest.getId());
        assertNull(requestIP.getId());

        dbWriter.create(loanRequest);
        assertNotNull(loanRequest.getId());
        assertNotNull(requestIP.getId());
    }

    @Test
    @Transactional
    public void testDBWriter_2() throws DBException
    {
        //scenario with new loan request coming from registered ip address
        createLoanRequest();
        requestIPRepository.create(requestIP);
        assertNotNull(requestIP.getId());

        dbWriter.create(loanRequest);
        assertNotNull(loanRequest.getId());
    }

}

package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.fixtures.RequestIPFixture;
import com.minibank.core.model.Customer;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.helpers.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;


public class LoanRequestRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private TestCustomerRepository testCustomerRepository;

    private LoanRequest loanRequest;
    private Customer customer;
    private String requestIp;

    @Before
    public void setUp() {
        dbCleaner.clear();
        requestIp = RequestIPFixture.IP;
        customer = CustomerFixture.standardCustomer();
        testCustomerRepository.create(customer);
        createLoanRequest();
    }

    @Test
    public void testCreate() {
        loanRequestRepository.create(loanRequest);
        assertNotNull(loanRequest.getId());
    }

    @Test
    public void testGetByRequestIp() {
        createLoanRequest();
        loanRequestRepository.create(loanRequest);
        createLoanRequest();
        loanRequestRepository.create(loanRequest);
        List<LoanRequest> loanRequests = loanRequestRepository.getByRequestIp(requestIp);
        assertEquals(2, loanRequests.size());
    }

    private void createLoanRequest() {
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIp(requestIp);
    }

}

package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.CustomerFixture;
import com.minibank.core.fixtures.LoanRequestFixture;
import com.minibank.core.model.Customer;
import com.minibank.core.model.LoanRequest;
import com.minibank.testutil.repositories.DatabaseCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class LoanRequestRepositoryTest extends SpringContextTest {

    public static final String IP = "127.0.0.1";

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private CustomerRepository customerRepository;

    private Customer customer;

    @Before
    public void setUp() {
        databaseCleaner.clear();
        customer = CustomerFixture.standardCustomer();
        customerRepository.create(customer);
    }

    @Test
    public void testCreate() {
        LoanRequest loanRequest = createLoanRequest();
        loanRequestRepository.create(loanRequest);
        assertNotNull(loanRequest.getId());
    }

    @Test
    public void testGetByRequestIp() {
        LoanRequest loanRequest1 = createLoanRequest();
        loanRequestRepository.create(loanRequest1);
        LoanRequest loanRequest2 = createLoanRequest();
        loanRequestRepository.create(loanRequest2);
        List<LoanRequest> loanRequests = loanRequestRepository.getByRequestIp(IP);
        assertEquals(2, loanRequests.size());
    }

    private LoanRequest createLoanRequest() {
        LoanRequest loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIp(IP);
        return loanRequest;
    }

}

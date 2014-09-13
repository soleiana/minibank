package com.minibank.core.services.helpers;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.repository.*;
import com.minibank.core.repository.tools.DBCleaner;
import com.minibank.core.services.common.DateTimeUtility;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static junit.framework.TestCase.assertTrue;

/**
 * Created by Ann on 13/09/14.
 */
public class CreditExpertImplTest extends SpringContextTest
{
    @Autowired
    private DBCleaner dbCleaner;
    @Autowired
    private LoanRequestRepository loanRequestRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RequestIPRepository requestIPRepository;
    @Autowired
    private BankParamsRepository bankParamsRepository;
    @Autowired
    private CreditExpert expert;

    private BankParams bankParams;
    private LoanRequest loanRequest;
    private RequestIP requestIP;
    private Customer customer;
    private List<LoanRequest> loanRequests = new ArrayList<>();

    @Before
    @Transactional
    public void setUp() throws DBException
    {
        dbCleaner.clear();
        bankParams = BankParamsFixture.standardBankParams();
        bankParamsRepository.create(bankParams);
        requestIP = RequestIPFixture.standardRequestIP();
        requestIPRepository.create(requestIP);

        createLoanRequest();
    }

    private void createLoanRequest() throws DBException
    {
        customer = CustomerFixture.standardCustomer();
        loanRequest = LoanRequestFixture.standardLoanRequest();
        customerRepository.create(customer);
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIP(requestIP);
        loanRequests.add(loanRequest);
        requestIP.setLoanRequests(loanRequests);
        loanRequestRepository.create(loanRequest);
    }

    @Test
    @Transactional
    public void testHasRisks_1() throws DBException
    {
        assertTrue(expert.hasRisks(loanRequest));

        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        Time riskTimeStart = bankParams.getRiskTimeStart();
        Time submissionTime = DateTimeUtility.increaseTime(riskTimeStart,1);

        loanRequest.setAmount(maxLoanAmount);
        loanRequest.setSubmissionTime(submissionTime);

        assertTrue(!expert.hasRisks(loanRequest));
    }
    @Test
    @Transactional
    public void testHasRisks_2() throws DBException
    {
        Date now = new Date();
        java.sql.Date sqlNow = DateTimeUtility.getSqlDate(now);

        for(int i = 0; i < bankParams.getMaxLoanAttempts(); i++)
        {
            createLoanRequest();
            loanRequest.setSubmissionDate(sqlNow);
        }
        assertTrue(!expert.hasRisks(loanRequest));

    }
}

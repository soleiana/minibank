package com.minibank.core.services.helpers;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.*;
import com.minibank.core.repositories.*;
import com.minibank.core.repositories.tools.DBCleaner;
import com.minibank.core.services.common.DateTimeUtility;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
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

    @Before
    @Transactional
    public void setUp() throws DBException
    {
        dbCleaner.clear();
        bankParams = BankParamsFixture.standardBankParams();
        bankParamsRepository.create(bankParams);
        requestIP = RequestIPFixture.standardRequestIP();
        requestIPRepository.create(requestIP);
    }

    private void createLoanRequest() throws DBException
    {
        Customer customer = CustomerFixture.standardCustomer();
        loanRequest = LoanRequestFixture.standardLoanRequest();
        customerRepository.create(customer);
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIP(requestIP);
    }

    @Test
    @Transactional
    public void testHasRisks_1() throws DBException
    {
        //negative loan request scenario with max loan amount in risk time
        //loan request gets rejected

        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        Time riskTimeStart = bankParams.getRiskTimeStart();
        Time submissionTime = DateTimeUtility.increaseTime(riskTimeStart,1);

        createLoanRequest();
        loanRequest.setAmount(maxLoanAmount);
        loanRequest.setSubmissionTime(submissionTime);
        loanRequestRepository.create(loanRequest);

        assertTrue(expert.hasRisks(loanRequest));
    }
    @Test
    @Transactional
    public void testHasRisks_2() throws DBException
    {
        //negative loan request scenario with max number of loan requests (attempts)
        //during one day

        Date now = new Date();
        java.sql.Date sqlNow = DateTimeUtility.getSqlDate(now);

        for(int i = 0; i < bankParams.getMaxLoanAttempts(); i++)
        {
            createLoanRequest();
            loanRequest.setSubmissionDate(sqlNow);
            loanRequestRepository.create(loanRequest);
        }
        assertTrue(expert.hasRisks(loanRequest));
    }

    @Test
    @Transactional
    public void testHasRisks_3() throws DBException
    {
        //positive loan request scenario with loan amount below maximum in no risk time
        //loan request gets accepted

        createLoanRequest();
        loanRequestRepository.create(loanRequest);
        assertTrue(!expert.hasRisks(loanRequest));
    }

    @Test
    @Transactional
    public void testHasRisks_4() throws DBException
    {
        //positive loan request scenario with loan amount below maximum in no risk time
        //loan requests get accepted (maxLoanAttempts - 1) times

        Date now = new Date();
        java.sql.Date sqlNow = DateTimeUtility.getSqlDate(now);

        for(int i = 0; i < bankParams.getMaxLoanAttempts()-1; i++)
        {
            createLoanRequest();
            loanRequest.setSubmissionDate(sqlNow);
            loanRequestRepository.create(loanRequest);
        }
        assertTrue(!expert.hasRisks(loanRequest));
    }
}

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

import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.assertNotNull;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;

/**
 * Created by Ann on 13/09/14.
 */
public class ConstraintCheckerImplTest extends SpringContextTest
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
    private ConstraintChecker checker;

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

        createLoanRequest(LoanRequestFixture.SUBMISSION_DATE);
    }

    private void createLoanRequest(java.sql.Date submissionDate) throws DBException
    {
        Customer customer = CustomerFixture.standardCustomer();
        loanRequest = LoanRequestFixture.standardLoanRequest();
        loanRequest.setSubmissionDate(submissionDate);
        customerRepository.create(customer);
        loanRequest.setCustomer(customer);
        loanRequest.setRequestIP(requestIP);
        loanRequestRepository.create(loanRequest);
    }

    @Test
    @Transactional
    public void testCheckMaxRequestsPerIP() throws DBException
    {
        bankParams.setMaxLoanAttempts(new Byte("3"));
        bankParamsRepository.update(bankParams);

        Date now = new Date();
        java.sql.Date sqlNow = DateTimeUtility.getSqlDate(now);

        assertNotNull(sqlNow);

        createLoanRequest(sqlNow);
        assertTrue(checker.checkMaxRequestsPerIP(loanRequest));
        createLoanRequest(sqlNow);
        assertTrue(checker.checkMaxRequestsPerIP(loanRequest));
        createLoanRequest(sqlNow);
        assertTrue(!checker.checkMaxRequestsPerIP(loanRequest));
    }

    @Test
    @Transactional
    public void testCheckTimeConstraint_1() throws DBException
    {
        //check loan requests for risk interval [00:00:00-07:00:00]

        Time riskTimeStart = bankParams.getRiskTimeStart();
        Time riskTimeEnd = bankParams.getRiskTimeEnd();

        //loan request in risk interval
        Time submissionTime = DateTimeUtility.increaseTime(riskTimeStart,1);
        loanRequest.setSubmissionTime(submissionTime);
        assertTrue(!checker.checkTimeConstraint(loanRequest));

        submissionTime = DateTimeUtility.increaseTime(riskTimeEnd,-1);
        loanRequest.setSubmissionTime(submissionTime);
        assertTrue(!checker.checkTimeConstraint(loanRequest));

        //loan request not in risk interval
        submissionTime = DateTimeUtility.increaseTime(riskTimeStart,-1);
        loanRequest.setSubmissionTime(submissionTime);
        assertTrue(checker.checkTimeConstraint(loanRequest));

        submissionTime = DateTimeUtility.increaseTime(riskTimeEnd,1);
        loanRequest.setSubmissionTime(submissionTime);
        assertTrue(checker.checkTimeConstraint(loanRequest));
    }

    @Test
    @Transactional
    public void testCheckTimeConstraint_2() throws DBException
    {
        //check loan requests for risk interval [22:00:00-08:00:00]

        BankParams newBankParams = BankParamsFixture.newBankParams();
        bankParamsRepository.create(newBankParams);

        Time riskTimeStart = newBankParams.getRiskTimeStart();
        Time riskTimeEnd = newBankParams.getRiskTimeEnd();

        //loan request in risk interval
        Time submissionTime = DateTimeUtility.increaseTime(riskTimeStart,1);
        loanRequest.setSubmissionTime(submissionTime);
        assertTrue(!checker.checkTimeConstraint(loanRequest));

        //loan request not in risk interval
        submissionTime = DateTimeUtility.increaseTime(riskTimeEnd,1);
        loanRequest.setSubmissionTime(submissionTime);
        assertTrue(checker.checkTimeConstraint(loanRequest));

        submissionTime = DateTimeUtility.increaseTime(riskTimeStart,-1);
        loanRequest.setSubmissionTime(submissionTime);
        assertTrue(checker.checkTimeConstraint(loanRequest));
    }

    @Test
    @Transactional
    public void testCheckAmountConstraint() throws DBException
    {
        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        loanRequest.setAmount(maxLoanAmount);

        assertTrue(!checker.checkAmountConstraint(loanRequest));

        BigDecimal loanAmount = bankParams.getMaxLoanAmount().subtract(new BigDecimal(1.00));
        loanRequest.setAmount(loanAmount);

        assertTrue(checker.checkAmountConstraint(loanRequest));
    }
}

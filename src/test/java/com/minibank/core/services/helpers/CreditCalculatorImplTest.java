package com.minibank.core.services.helpers;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.BankParams;
import com.minibank.core.domain.BankParamsFixture;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.domain.LoanRequestFixture;
import com.minibank.core.repository.BankParamsRepository;
import com.minibank.core.repository.DBException;
import com.minibank.core.repository.tools.DBCleaner;
import com.minibank.core.services.common.Number;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;

/**
 * Created by Ann on 13/09/14.
 */
public class CreditCalculatorImplTest extends SpringContextTest
{
    @Autowired
    private DBCleaner dbCleaner;
    @Autowired
    private BankParamsRepository bankParamsRepository;
    @Autowired
    private CreditCalculator creditCalculator;

    private BankParams bankParams;
    private LoanRequest loanRequest;

    @Before
    @Transactional
    public void setUp() throws DBException
    {
        dbCleaner.clear();
        bankParams = BankParamsFixture.standardBankParams();
        bankParamsRepository.create(bankParams);
        loanRequest = LoanRequestFixture.standardLoanRequest();
    }

    @Test
    @Transactional
    public void testGetLoanEndDate() throws DBException
    {
        Date submissionDate = Date.valueOf("2014-09-01");
        loanRequest.setSubmissionDate(submissionDate);
        loanRequest.setTerm(20);
        Date endDate = creditCalculator.getLoanEndDate(loanRequest);
        assertEquals(Date.valueOf("2014-09-21"),endDate);
    }

    @Test
    @Transactional
    public void testGetInterest() throws DBException
    {
        BigDecimal amount = new BigDecimal("200.00");
        Integer term = 20;
        BigDecimal t = new BigDecimal("20");
        BigDecimal interestRate = bankParams.getBaseInterestRate();
        BigDecimal expectedInterest = amount.multiply(interestRate)
                                            .multiply(t)
                                            .multiply(Number.FACTOR);
        expectedInterest = expectedInterest.setScale(2, RoundingMode.HALF_EVEN);
        loanRequest.setAmount(amount);
        loanRequest.setTerm(term);
        BigDecimal interest = creditCalculator.getInterest(loanRequest);
        assertTrue(interest.compareTo(expectedInterest) == 0);

    }
}

package com.minibank.core.repository;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.BankParams;
import com.minibank.core.domain.BankParamsFixture;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;



/**
 * Created by Ann on 09/09/14.
 */
public class BankParamsRepositoryImplTest extends SpringContextTest
{
    @Autowired
    private BankParamsRepository bankParamsRepository;

    private BankParams bankParams;

    @Before
    public void setUp() throws Exception
    {
        bankParams = BankParamsFixture.standardBankParams();
    }

    @Test
    @Transactional
    public void testCreate() throws Exception
    {
        bankParamsRepository.create(bankParams);
        assertNotNull(bankParams.getId());
    }

    @Test
    @Transactional
    public void testGetById() throws Exception
    {
        bankParamsRepository.create(bankParams);
        Integer id = bankParams.getId();
        assertEquals(bankParams, bankParamsRepository.getById(id));
    }

    @Test
    @Transactional
    public void testUpdate() throws Exception
    {
        bankParamsRepository.create(bankParams);
        BankParams newBankParams = BankParamsFixture.newBankParams();

        bankParams.setMaxLoanAmount(newBankParams.getMaxLoanAmount());
        bankParams.setBaseInterestRate(newBankParams.getBaseInterestRate());
        bankParams.setInterestRateFactor(newBankParams.getInterestRateFactor());
        bankParams.setMaxLoanAttempts(newBankParams.getMaxLoanAttempts());
        bankParams.setRiskTimeStart(newBankParams.getRiskTimeStart());
        bankParams.setRiskTimeEnd(newBankParams.getRiskTimeEnd());

        bankParamsRepository.update(bankParams);

        assertEquals(newBankParams.getMaxLoanAmount(), bankParams.getMaxLoanAmount());
        assertEquals(newBankParams.getBaseInterestRate(), bankParams.getBaseInterestRate());
        assertEquals(newBankParams.getInterestRateFactor(), bankParams.getInterestRateFactor());
        assertEquals(newBankParams.getMaxLoanAttempts(), bankParams.getMaxLoanAttempts());
        assertEquals(newBankParams.getRiskTimeStart(), bankParams.getRiskTimeStart());
        assertEquals(newBankParams.getRiskTimeEnd(), bankParams.getRiskTimeEnd());
    }
}

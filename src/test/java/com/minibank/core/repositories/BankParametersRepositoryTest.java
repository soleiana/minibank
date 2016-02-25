package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.BankParametersFixture;
import com.minibank.core.repositories.tools.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertEquals;


public class BankParametersRepositoryTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private BankParametersRepository bankParametersRepository;
    private BankParameters bankParameters;

    @Before
    @Transactional
    public void setUp() {
        dbCleaner.clear();
        bankParameters = BankParametersFixture.standardBankParameters();
    }

    @Test
    @Transactional
    public void testCreate() {
        bankParametersRepository.create(bankParameters);
        assertNotNull(bankParameters.getId());
    }

    @Test
    @Transactional
    public void testUpdate() {
        bankParametersRepository.create(bankParameters);
        BankParameters newBankParams = BankParametersFixture.newBankParameters();

        bankParameters.setMaxLoanAmount(newBankParams.getMaxLoanAmount());
        bankParameters.setBaseInterestRate(newBankParams.getBaseInterestRate());
        bankParameters.setInterestRateFactor(newBankParams.getInterestRateFactor());
        bankParameters.setMaxLoanAttempts(newBankParams.getMaxLoanAttempts());
        bankParameters.setRiskTimeStart(newBankParams.getRiskTimeStart());
        bankParameters.setRiskTimeEnd(newBankParams.getRiskTimeEnd());
        bankParameters.setLoanExtensionTerm(newBankParams.getLoanExtensionTerm());

        bankParametersRepository.update(bankParameters);

        assertEquals(newBankParams.getMaxLoanAmount(), bankParameters.getMaxLoanAmount());
        assertEquals(newBankParams.getBaseInterestRate(), bankParameters.getBaseInterestRate());
        assertEquals(newBankParams.getInterestRateFactor(), bankParameters.getInterestRateFactor());
        assertEquals(newBankParams.getMaxLoanAttempts(), bankParameters.getMaxLoanAttempts());
        assertEquals(newBankParams.getRiskTimeStart(), bankParameters.getRiskTimeStart());
        assertEquals(newBankParams.getRiskTimeEnd(), bankParameters.getRiskTimeEnd());
        assertEquals(newBankParams.getLoanExtensionTerm(), bankParameters.getLoanExtensionTerm());
    }

    @Test
    @Transactional
    public void testGetLast() {
        BankParameters bp1 = BankParametersFixture.standardBankParameters();
        bp1.setLoanExtensionTerm(BankParametersFixture.NEW_LOAN_EXTENSION_TERM);
        BankParameters bp2 = BankParametersFixture.standardBankParameters();
        bp2.setInterestRateFactor(BankParametersFixture.NEW_INTEREST_RATE_FACTOR);

        bankParametersRepository.create(bankParameters);
        bankParametersRepository.create(bp1);
        bankParametersRepository.create(bp2);

        BankParameters bp = bankParametersRepository.getLast();
        assertEquals(bp2,bp);
    }
}

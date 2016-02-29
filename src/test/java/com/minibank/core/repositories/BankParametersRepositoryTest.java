package com.minibank.core.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.model.BankParameters;
import com.minibank.testutil.repositories.DatabaseCleaner;
import com.minibank.testutil.repositories.TestBankParametersRepository;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertEquals;


public class BankParametersRepositoryTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private TestBankParametersRepository testBankParametersRepository;

    @Autowired
    private BankParametersRepository bankParametersRepository;


    @Before
    public void setUp() {
        databaseCleaner.clear();
    }

    @Test
    public void testGetLast() {
        BankParameters bankParameters1 = BankParametersFixture.standardBankParameters();
        bankParameters1.setLoanExtensionTerm(BankParametersFixture.NEW_LOAN_EXTENSION_TERM);
        BankParameters bankParameters2 = BankParametersFixture.standardBankParameters();
        bankParameters2.setInterestRateFactor(BankParametersFixture.NEW_INTEREST_RATE_FACTOR);
        testBankParametersRepository.create(bankParameters1);
        testBankParametersRepository.create(bankParameters2);

        BankParameters retrievedBankParameters = bankParametersRepository.getCurrentBankParameters();
        assertEquals(bankParameters2, retrievedBankParameters);
    }
}

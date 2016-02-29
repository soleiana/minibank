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
import static org.junit.Assert.assertNull;

public class RepositoryTemplateMethodTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private TestBankParametersRepository testBankParametersRepository;

    @Autowired
    private RepositoryTemplateMethod<BankParameters> repositoryTemplateMethod;


    @Before
    public void setUp()  {
        databaseCleaner.clear();
    }

    @Test
    public void testGetLastIfNoRecordsExist() {
        BankParameters retrievedBankParameters = repositoryTemplateMethod.getLast(BankParameters.class);
        assertNull(retrievedBankParameters);
    }

    @Test
    public void testGetLastIfOneRecordExists() {
        BankParameters bankParameters = BankParametersFixture.standardBankParameters();
        testBankParametersRepository.create(bankParameters);
        BankParameters retrievedBankParameters = repositoryTemplateMethod.getLast(BankParameters.class);
        assertEquals(bankParameters, retrievedBankParameters);
    }

    @Test
    public void testGetLastIfFewRecordsExist() {
        BankParameters bankParameters1 = BankParametersFixture.standardBankParameters();
        testBankParametersRepository.create(bankParameters1);
        BankParameters bankParameters2 = BankParametersFixture.newBankParameters();
        testBankParametersRepository.create(bankParameters2);
        BankParameters retrievedBankParameters = repositoryTemplateMethod.getLast(BankParameters.class);
        assertEquals(bankParameters2, retrievedBankParameters);
    }
}
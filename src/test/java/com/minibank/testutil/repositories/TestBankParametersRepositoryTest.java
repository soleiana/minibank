package com.minibank.testutil.repositories;

import com.minibank.SpringContextTest;
import com.minibank.core.fixtures.BankParametersFixture;
import com.minibank.core.model.BankParameters;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.assertNotNull;

public class TestBankParametersRepositoryTest extends SpringContextTest {

    @Autowired
    private TestBankParametersRepository testBankParametersRepository;

    @Test
    public void testCreate() {
        BankParameters bankParameters = BankParametersFixture.standardBankParameters();
        testBankParametersRepository.create(bankParameters);
        assertNotNull(bankParameters.getId());
    }

}
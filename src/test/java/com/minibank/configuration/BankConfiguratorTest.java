package com.minibank.configuration;

import com.minibank.SpringContextTest;
import com.minibank.core.model.BankParameters;
import com.minibank.core.repositories.BankParametersRepository;
import com.minibank.core.repositories.helpers.DBCleaner;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class BankConfiguratorTest extends SpringContextTest {

    @Autowired
    private DBCleaner dbCleaner;

    @Autowired
    private BankConfigurator bankConfigurator;

    @Autowired
    private BankParametersRepository bankParametersRepository;


    @Before
    public void setUp() {
        dbCleaner.clear();
    }

    @Test
    public void testSetParameters() {
        bankConfigurator.setParameters();
        BankParameters bankParameters = bankParametersRepository.getCurrentBankParameters();
        assertNotNull(bankParameters);
        assertEquals(new BigDecimal("4000.00"), bankParameters.getMaxLoanAmount());
        assertEquals(new Byte("3"), bankParameters.getMaxLoanAttempts());
        assertEquals(new BigDecimal("100.00"), bankParameters.getBaseInterestRate());
        assertEquals(new BigDecimal("1.50"), bankParameters.getInterestRateFactor());
        assertEquals(LocalTime.of(0, 0, 0), bankParameters.getRiskTimeStart());
        assertEquals(LocalTime.of(7, 0, 0), bankParameters.getRiskTimeEnd());
        assertEquals(new Short("7"), bankParameters.getLoanExtensionTerm());
    }
}
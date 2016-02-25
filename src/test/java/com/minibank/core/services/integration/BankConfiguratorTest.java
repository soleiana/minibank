package com.minibank.core.services.integration;

import com.minibank.SpringContextTest;
import com.minibank.core.model.BankParameters;
import com.minibank.core.repositories.BankParametersRepository;
import com.minibank.core.repositories.tools.DBCleaner;
import com.minibank.core.services.BankConfigurator;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.sql.Time;

import static org.junit.Assert.*;

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
        BankParameters bankParameters = bankParametersRepository.getLast();
        assertNotNull(bankParameters);
        assertEquals(new BigDecimal("4000.00"), bankParameters.getMaxLoanAmount());
        assertEquals(new Byte("3"), bankParameters.getMaxLoanAttempts());
        assertEquals(new BigDecimal("100.00"), bankParameters.getBaseInterestRate());
        assertEquals(new BigDecimal("1.50"), bankParameters.getInterestRateFactor());
        assertEquals(Time.valueOf("00:00:00"), bankParameters.getRiskTimeStart());
        assertEquals(Time.valueOf("07:00:00"), bankParameters.getRiskTimeEnd());
        assertEquals(new Short("7"), bankParameters.getLoanExtensionTerm());
    }
}
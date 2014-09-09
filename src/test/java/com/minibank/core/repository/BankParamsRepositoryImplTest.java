package com.minibank.core.repository;

import com.minibank.SpringContextTest;
import com.minibank.core.domain.BankParams;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import java.math.BigDecimal;
import java.sql.Time;



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
        Time riskStartTime = Time.valueOf("00:00:00");
        Time riskEndTime = Time.valueOf("07:00:00");

        bankParams = new BankParams(new BigDecimal("4000.00"),
                                    new BigDecimal("100.00"),
                                    new BigDecimal("1.5"),
                                    new Byte("3"),
                                    riskStartTime,
                                    riskEndTime);
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
}

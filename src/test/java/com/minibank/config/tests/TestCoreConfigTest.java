package com.minibank.config.tests;

/**
 * Created by Ann on 07/09/14.
 */

import com.minibank.config.TestCoreConfig;
import com.minibank.core.services.LoanService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.minibank.core.repository.BankParamsRepository;

import static junit.framework.TestCase.assertNotNull;


public class TestCoreConfigTest
{
    private ApplicationContext context;

    @Before
    public void setUp()
    {
        context = new AnnotationConfigApplicationContext(TestCoreConfig.class);
    }
    @Test
    public void testGetBeanByClass()
    {
        BankParamsRepository  repository = context.getBean(BankParamsRepository.class);
        LoanService loanService = context.getBean(LoanService.class);
        assertNotNull(repository);
        assertNotNull(loanService);

    }
}

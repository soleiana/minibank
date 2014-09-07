package com.minibank;

/**
 * Created by Ann on 07/09/14.
 */

import com.minibank.core.services.LoanService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.minibank.core.repository.BankParamsRepository;
import org.springframework.stereotype.Component;

import static junit.framework.TestCase.assertNotNull;



public class TestConfigTest
{
    private ApplicationContext context;

    @Before
    public void setUp()
    {
        context = new AnnotationConfigApplicationContext(TestConfig.class);
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

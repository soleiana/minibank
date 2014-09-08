package com.minibank.config;

import com.minibank.core.repository.BankParamsRepository;
import com.minibank.core.services.LoanService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static junit.framework.Assert.assertNotNull;

/**
 * Created by Ann on 08/09/14.
 */
public class CoreConfigTest
{
    @Test
    public void testCoreConfig()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(CoreConfig.class);
        LoanService loanService = context.getBean(LoanService.class);
        BankParamsRepository bankParamsRepository = loanService.getRepository();

        assertNotNull(loanService);
        assertNotNull(bankParamsRepository);

    }
}

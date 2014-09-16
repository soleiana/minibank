package com.minibank.config;

import com.minibank.core.services.LoanService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Ann on 08/09/14.
 */
public class RestConfigTest
{
    @Test
    public void testCoreConfig()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(RestConfig.class);
        LoanService loanService = context.getBean(LoanService.class);

        assertNotNull(loanService);
    }
}

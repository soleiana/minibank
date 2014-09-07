package com.minibank.core.services;

import com.minibank.SpringContextTest;
import com.minibank.TestConfigTest;
import com.minibank.TestConfig;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

/**
 * Created by Ann on 07/09/14.
 */

public class LoanEventHandlerTest extends SpringContextTest
{
    @Autowired
    private LoanService loanService;

    @Test
    public void testSomeMethod() throws Exception
    {
        assertNotNull(loanService);
        loanService.someMethod();
        assertEquals(loanService.getSomeValue(),"some");
    }
}

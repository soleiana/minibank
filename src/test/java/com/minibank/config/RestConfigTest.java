package com.minibank.config;

import com.minibank.core.services.QueryExecutor;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static junit.framework.TestCase.assertNotNull;


public class RestConfigTest
{
    @Test
    public void testCoreConfig()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(RestConfig.class);

        QueryExecutor queryExecutor = context.getBean(QueryExecutor.class);

        assertNotNull(queryExecutor);
    }
}

package com.minibank.configuration;


import com.minibank.core.services.CreateLoanQueryHandler;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotNull;


public class RestConfigurationTest {

    @Test
    public void testRestConfiguration() {
        ApplicationContext context = new AnnotationConfigApplicationContext(RestConfiguration.class);
        CreateLoanQueryHandler queryHandler = context.getBean(CreateLoanQueryHandler.class);
        assertNotNull(queryHandler);
    }
}

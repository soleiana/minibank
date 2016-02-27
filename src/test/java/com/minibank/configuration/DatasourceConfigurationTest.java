package com.minibank.configuration;


import com.minibank.core.repositories.BankParametersRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotNull;


public class DatasourceConfigurationTest {

    @Test
    public void testDatasourceConfiguration() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DatasourceConfiguration.class);
        BankParametersRepository bankParametersRepository = context.getBean(BankParametersRepository.class);
        assertNotNull(bankParametersRepository);
    }

}

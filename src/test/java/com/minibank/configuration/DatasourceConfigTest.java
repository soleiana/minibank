package com.minibank.configuration;


import com.minibank.core.repositories.BankParametersRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static junit.framework.TestCase.assertNotNull;


public class DatasourceConfigTest {

    @Test
    public void testDatasourceConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DatasourceConfiguration.class);
        BankParametersRepository bankParametersRepository = context.getBean(BankParametersRepository.class);
        assertNotNull(bankParametersRepository);
    }

}

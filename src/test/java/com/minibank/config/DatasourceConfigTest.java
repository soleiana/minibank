package com.minibank.config;


import com.minibank.core.repositories.BankParametersRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static junit.framework.TestCase.assertNotNull;


public class DatasourceConfigTest {

    @Test
    public void testDatasourceConfig() {
        ApplicationContext context = new AnnotationConfigApplicationContext(DatasourceConfig.class);
        BankParametersRepository bankParametersRepository = context.getBean(BankParametersRepository.class);
        assertNotNull(bankParametersRepository);
    }

}

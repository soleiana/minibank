package com.minibank.config.tests;

/**
 * Created by Ann on 07/09/14.
 */

import com.minibank.config.DatasourceConfig;
import com.minibank.core.repository.BankParamsRepository;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static junit.framework.Assert.assertNotNull;


public class DatasourceConfigTest
{

    @Test
    public void testDatasourceConfig()
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(DatasourceConfig.class);

        BankParamsRepository bankParamsRepository = context.getBean(BankParamsRepository.class);

        assertNotNull(bankParamsRepository);

    }

}

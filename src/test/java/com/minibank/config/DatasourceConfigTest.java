package com.minibank.config;

/**
 * Created by Ann on 07/09/14.
 */
import com.minibank.TestConfig;
import com.minibank.core.repository.BankParamsRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

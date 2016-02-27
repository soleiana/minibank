package com.minibank;


import com.minibank.configuration.DatasourceConfiguration;
import com.minibank.configuration.RestConfiguration;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestConfiguration.class, DatasourceConfiguration.class})
@Rollback(false)
@Transactional(transactionManager = "transactionManager")
@Ignore
public class SpringContextTest {}

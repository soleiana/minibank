package com.minibank;
/**
 * Created by Ann on 07/09/14.
 */

import com.minibank.config.TestCoreConfig;
import com.minibank.config.TestDatasourceConfig;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestCoreConfig.class, TestDatasourceConfig.class})
@TransactionConfiguration(transactionManager = "testTransactionManager", defaultRollback = false)
@Ignore
public class SpringContextTest
{}

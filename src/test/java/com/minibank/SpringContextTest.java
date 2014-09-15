package com.minibank;
/**
 * Created by Ann on 07/09/14.
 */

import com.minibank.config.RestConfig;
import com.minibank.config.DatasourceConfig;
import org.junit.Ignore;
import org.junit.runner.RunWith;
import org.springframework.test.context.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RestConfig.class, DatasourceConfig.class})
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
@Ignore
public class SpringContextTest
{}

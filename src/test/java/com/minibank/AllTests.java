package com.minibank;

/**
 * Created by Ann on 08/09/14.
 */

import com.minibank.config.tests.TestCoreConfigTest;
import com.minibank.core.repository.BankParamsRepositoryImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        TestCoreConfigTest.class,
        BankParamsRepositoryImplTest.class
          })
public class AllTests
{}

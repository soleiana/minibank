package com.minibank;

/**
 * Created by Ann on 08/09/14.
 */

import com.minibank.config.tests.TestCoreConfigTest;
import com.minibank.core.repository.BankParamsRepositoryImplTest;
import com.minibank.core.repository.CustomerRepositoryImplTest;
import com.minibank.core.repository.LoanRequestRepositoryImplTest;
import com.minibank.core.repository.RequestIPRepositoryImplTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        TestCoreConfigTest.class,
        BankParamsRepositoryImplTest.class,
        CustomerRepositoryImplTest.class,
        RequestIPRepositoryImplTest.class,
        LoanRequestRepositoryImplTest.class

          })
public class AllTests
{}

package com.minibank;

/**
 * Created by Ann on 08/09/14.
 */

import com.minibank.config.CoreConfigTest;
import junit.framework.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
        CoreConfigTest.class,
          })
public class AllTests
{}

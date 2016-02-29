package com.minibank.rest.controllers.integration;

import com.minibank.SpringContextTest;
import com.minibank.testutil.repositories.DatabaseCleaner;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class LoanExtensionControllerIntegrationTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setUp() {
        databaseCleaner.clear();
    }
}

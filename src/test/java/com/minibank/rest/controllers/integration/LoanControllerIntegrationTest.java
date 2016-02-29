package com.minibank.rest.controllers.integration;

import com.minibank.SpringContextTest;
import com.minibank.core.repositories.helpers.DatabaseCleaner;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class LoanControllerIntegrationTest extends SpringContextTest {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Before
    public void setUp() {
        databaseCleaner.clear();
    }


}

package com.minibank.rest.controllers.integration;

import org.junit.Before;

import static com.jayway.restassured.RestAssured.delete;
import static com.jayway.restassured.RestAssured.post;

public class IntegrationTest {

    @Before
    public void setUp() {
        delete("/minibank/test-util/clean-database");
        post("/minibank/test-util/bank-parameters");
    }
}

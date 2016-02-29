package com.minibank.rest.controllers.integration;

import com.minibank.rest.fixtures.LoanRequestFixture;
import com.minibank.rest.model.LoanRequest;
import org.junit.Before;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.*;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

public class LoanControllerIntegrationTest {

    @Before
    public void setUp() {
        delete("/minibank/test-util/clean-database");
        post("/minibank/test-util/bank-parameters");
    }

    @Test
    public void testCreateLoan() {
        int customerId = getCustomerId();
        LoanRequest loanRequest = LoanRequestFixture.standardLoanRequest(customerId);
        given().
                contentType("application/json").
                body(loanRequest).
                when().
                post("/minibank/loans").
                then().
                contentType("application/json").
                assertThat().
                statusCode(201).
                assertThat().
                body(equalTo("Loan obtained successfully"));
    }

    private int getCustomerId() {
        String id = when().
                post("/minibank/test-util/customers").
                then().
                contentType("application/json").
                assertThat().
                statusCode(201).
                assertThat().
                body(notNullValue()).
                extract().response().asString();
        return Integer.parseInt(id);
    }

}

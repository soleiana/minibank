package com.minibank.rest.controllers.integration;

import com.minibank.rest.fixtures.LoanRequestFixture;
import com.minibank.rest.model.LoanRequest;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoanControllerIntegrationTest  extends IntegrationTest {

    @Test
    public void testCreateLoan() {
        int customerId = RestResourceManager.getCustomerId();
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
}

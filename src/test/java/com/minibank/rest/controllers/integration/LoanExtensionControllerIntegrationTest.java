package com.minibank.rest.controllers.integration;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LoanExtensionControllerIntegrationTest extends IntegrationTest {

    @Test
    public void testCreateLoanExtension() {
        Integer customerId = RestResourceManager.getCustomerId();
        Integer loanId = RestResourceManager.getLoanId(customerId);
        given().
                contentType("application/json").
                when().
                body(loanId).
                post("/minibank/loans/" + loanId.toString() + "/extensions").
                then().
                contentType("application/json").
                assertThat().
                statusCode(201).
                assertThat().
                body(equalTo("Loan extension obtained successfully"));
    }

}

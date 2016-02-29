package com.minibank.rest.controllers.integration;

import org.junit.Test;

import static com.jayway.restassured.RestAssured.given;

public class CustomerControllerIntegrationTest extends IntegrationTest {

    @Test
    public void testGetAllLoans() {
        Integer customerId = RestResourceManager.getCustomerId();
        Integer loanId = RestResourceManager.getLoanId(customerId);
        RestResourceManager.createLoanExtension(loanId);
        given().
                when().
                get("/minibank/customers/" + customerId.toString() + "/loans").
                then().
                contentType("application/json").
                assertThat().
                statusCode(200);
    }

}

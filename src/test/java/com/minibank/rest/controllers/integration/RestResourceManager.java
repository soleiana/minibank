package com.minibank.rest.controllers.integration;

import com.minibank.rest.fixtures.LoanRequestFixture;
import com.minibank.rest.model.LoanRequest;

import static com.jayway.restassured.RestAssured.given;

public class RestResourceManager {

    public static int getCustomerId() {
        String id = given().
                post("/minibank/test-util/customers").
                then().
                contentType("application/json").
                extract().response().asString();
        return Integer.parseInt(id);
    }

    public static int getLoanId(Integer customerId) {
        createLoan(customerId);
        String id = given().
                get("/minibank/test-util/loans").
                then().
                contentType("application/json").
                extract().response().asString();
        return Integer.parseInt(id);
    }

    public static void createLoanExtension(Integer loanId) {
        given().
                contentType("application/json").
                body(loanId).
                post("/minibank/loans/" + loanId.toString() + "/extensions");
    }

    private static void createLoan(int customerId) {
        LoanRequest loanRequest = LoanRequestFixture.standardLoanRequest(customerId);
        given().contentType("application/json").
                body(loanRequest).
                post("/minibank/loans");
    }

}

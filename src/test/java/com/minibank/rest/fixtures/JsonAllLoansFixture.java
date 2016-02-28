package com.minibank.rest.fixtures;


public class JsonAllLoansFixture {

    public static String standardAllLoansResponse() {
        return "{\"customer\":{\"id\":2,\"name\":\"James\",\"surname\":\"Bond\"}," +
                "\"loans\":" +
                    "[{\"id\":5," +
                    "\"currInterestRate\":150," +
                    "\"currInterest\":600," +
                    "\"amount\":500," +
                    "\"startDate\":\"2014-09-01\"," +
                    "\"endDate\":\"2014-09-20\"," +
                    "\"loanExtensions\":" +
                        "[{\"interestRate\":150," +
                        "\"interest\":600," +
                        "\"startDate\":\"2014-09-10\"," +
                        "\"endDate\":\"2014-09-20\"," +
                        "\"submissionDate\":\"2014-09-10\"" +
                        "}]" +
                     "}]" +
                "}";
    }

}

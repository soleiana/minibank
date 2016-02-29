package com.minibank.rest.fixtures;


public class JsonAllLoansFixture {

    public static String standardAllLoansResponse() {
        return "{\"customer\":{\"id\":2,\"name\":\"James\",\"surname\":\"Bond\"}," +
                "\"loans\":" +
                  "[{\"id\":1," +
                  "\"currInterestRate\":100.00," +
                  "\"currInterest\":200.00," +
                  "\"amount\":500.00," +
                  "\"startDate\":\"2014-09-01\"," +
                  "\"endDate\":\"2014-09-30\"," +
                  "\"loanExtensions\":" +
                     "[{\"interestRate\":150.00," +
                     "\"interest\":300.00," +
                     "\"startDate\":\"2014-09-30\"," +
                     "\"endDate\":\"2014-10-06\"," +
                     "\"submissionDate\":\"2014-09-29\"" +
                    "}]" +
                  "}]" +
                "}";
    }

    public static String loansNotFoundResponse() {
        return "{\"customer\":{\"id\":2,\"name\":\"James\",\"surname\":\"Bond\"},\"loans\":[]}";
    }

    public static String emptyAllResponse() {
        return "{\"customer\":null,\"loans\":[]}";
    }

}

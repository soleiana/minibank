package com.minibank.rest.domain;

/**
 * Created by Ann on 16/09/14.
 */
public class RestDataFixture
{
    public static String standardLoanRequestJSON()
    {
        return "{\"customerId\": 2, \"requestIP\": null, \"term\": 30, \"amount\" : 200}";
    }

    public static String wrongLoanRequestJSON_1()
    {
        return "{\"customerId\": null, \"requestIP\": null, \"term\": 30, \"amount\" : 200}";
    }

    public static String wrongLoanRequestJSON_2()
    {
        return "{\"customerId\": 2, \"requestIP\": null, \"term\": -30, \"amount\" : 200}";
    }

}

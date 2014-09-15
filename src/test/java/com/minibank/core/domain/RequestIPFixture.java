package com.minibank.core.domain;

/**
 * Created by Ann on 10/09/14.
 */
public class RequestIPFixture
{
    private static final String IP = "127.0.0.1";

    public static RequestIP standardRequestIP()
    {
        RequestIP requestIP = new RequestIP();
        requestIP.setIP(IP);
        return requestIP;
    }
}

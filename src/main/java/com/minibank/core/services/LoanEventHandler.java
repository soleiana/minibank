package com.minibank.core.services;

import org.springframework.stereotype.Component;

/**
 * Created by Ann on 07/09/14.
 */
@Component
public class LoanEventHandler implements LoanService
{
    private  String someValue = "";
    public void someMethod()
    {
        someValue = "some";
    }
    public String getSomeValue()
    {
        return  someValue;
    }
}

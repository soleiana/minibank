package com.minibank.core.domain;

/**
 * Created by Ann on 08/09/14.
 */
public enum LoanRequestStatus
{
    NEW("NEW"), APPROVED("APPROVED"), REJECTED("REJECTED");

    private final String value;

    private LoanRequestStatus(String value)
    {
        this.value = value;
    }

    private String getValue()
    {
        return value;
    }

    @Override
    public String toString()
    {
        return this.getValue();
    }
}

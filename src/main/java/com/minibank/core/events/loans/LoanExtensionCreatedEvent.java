package com.minibank.core.events.loans;

import com.minibank.core.events.CreatedEvent;

/**
 * Created by Ann on 06/09/14.
 */
public class LoanExtensionCreatedEvent extends CreatedEvent
{
    private final String message;

    public LoanExtensionCreatedEvent(String message)
    {
        this.message = message;
    }

    public String getMessage()
    {
        return message;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LoanExtensionCreatedEvent that = (LoanExtensionCreatedEvent) o;

        if (message != null ? !message.equals(that.message) : that.message != null) return false;

        return true;
    }

    @Override
    public int hashCode()
    {
        return message != null ? message.hashCode() : 0;
    }
}

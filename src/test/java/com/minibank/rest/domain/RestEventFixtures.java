package com.minibank.rest.domain;

import com.minibank.core.events.loans.LoanCreatedEvent;
import com.minibank.core.services.common.Message;

/**
 * Created by Ann on 16/09/14.
 */
public class RestEventFixtures
{
    public static LoanCreatedEvent loanCreated(Boolean loanObtained, String message)
    {
        return new LoanCreatedEvent(loanObtained, message);
    }
}

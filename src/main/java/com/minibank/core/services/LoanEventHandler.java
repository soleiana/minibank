package com.minibank.core.services;

import com.minibank.core.events.loans.*;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 07/09/14.
 */
@Component
public class LoanEventHandler implements LoanService
{
    @Override
    public LoanCreatedEvent createLoan(CreateLoanEvent event)
    {return  null;}

    @Override
    public LoanExtensionCreatedEvent createLoanExtension(CreateLoanExtensionEvent event)
    {return  null;}

    @Override
    public AllLoansEvent requestAllLoans(RequestAllLoansEvent event)
    {return  null;}
}

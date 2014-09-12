package com.minibank.core.services;

import com.minibank.core.events.loans.*;


/**
 * Created by Ann on 06/09/14.
 */
public interface LoanService
{
    LoanCreatedEvent createLoan(CreateLoanEvent event);

    LoanExtensionCreatedEvent createLoanExtension(CreateLoanExtensionEvent event);

    AllLoansEvent requestAllLoans(RequestAllLoansEvent event);
}

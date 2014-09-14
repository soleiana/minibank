package com.minibank.core.services.factories;

import com.minibank.core.domain.LoanExtension;
import com.minibank.core.events.loans.domain.LoanExtensionDetails;
import com.minibank.core.repository.DBException;

/**
 * Created by Ann on 14/09/14.
 */
public interface LoanExtensionFactory
{
    LoanExtension getNewLoanExtension(LoanExtensionDetails loanExtensionDetails)
                                    throws DBException;
}
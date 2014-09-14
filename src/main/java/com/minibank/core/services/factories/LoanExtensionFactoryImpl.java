package com.minibank.core.services.factories;

import com.minibank.core.domain.LoanExtension;
import com.minibank.core.events.loans.LoanExtensionDetails;
import com.minibank.core.repository.DBException;

/**
 * Created by Ann on 14/09/14.
 */
public class LoanExtensionFactoryImpl implements LoanExtensionFactory
{
    @Override
    public LoanExtension getNewLoanExtension(LoanExtensionDetails loanExtensionDetails)
            throws DBException
    {
        return  null;
    }
}

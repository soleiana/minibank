package com.minibank.core.services.factories;

import com.minibank.core.domain.LoanExtension;
import com.minibank.core.repositories.DBException;

/**
 * Created by Ann on 14/09/14.
 */
public interface LoanExtensionFactory
{
    LoanExtension getNewLoanExtension(Integer loanId)
                                    throws DBException;
}

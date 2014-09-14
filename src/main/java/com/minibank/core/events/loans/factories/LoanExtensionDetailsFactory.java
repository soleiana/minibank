package com.minibank.core.events.loans.factories;

import com.minibank.core.events.loans.domain.LoanExtensionDetails;
import com.minibank.rest.domain.LoanExtensionRequest;

/**
 * Created by Ann on 14/09/14.
 */
public interface LoanExtensionDetailsFactory
{
    LoanExtensionDetails getNewLoanExtensionDetails(LoanExtensionRequest loanExtensionRequest);
}

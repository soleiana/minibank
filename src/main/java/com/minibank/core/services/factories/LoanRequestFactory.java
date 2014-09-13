package com.minibank.core.services.factories;

import com.minibank.core.domain.LoanRequest;
import com.minibank.core.events.loans.LoanRequestDetails;

/**
 * Created by Ann on 13/09/14.
 */
public interface LoanRequestFactory
{
    LoanRequest getNewLoanRequest(LoanRequestDetails loanRequestDetails);
}

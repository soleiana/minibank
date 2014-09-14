package com.minibank.core.events.loans.factories;

import com.minibank.core.events.loans.domain.LoanRequestDetails;
import com.minibank.rest.domain.LoanRequest;

/**
 * Created by Ann on 14/09/14.
 */
public interface LoanRequestDetailsFactory
{
    LoanRequestDetails getNewLoanRequestDetails(LoanRequest loanRequest);
}

package com.minibank.core.services.factories;

import com.minibank.core.domain.LoanRequest;
import com.minibank.core.events.loans.domain.LoanRequestDetails;
import com.minibank.core.repository.DBException;

/**
 * Created by Ann on 13/09/14.
 */
public interface LoanRequestFactory
{
    LoanRequest getNewLoanRequest(LoanRequestDetails loanRequestDetails)throws DBException;
}

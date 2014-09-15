package com.minibank.core.services.helpers;

import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repository.DBException;

/**
 * Created by Ann on 15/09/14.
 */
interface ConstraintChecker
{
    boolean checkMaxRequestsPerIP(LoanRequest loanRequest) throws DBException;

    boolean checkTimeConstraint(LoanRequest loanRequest) throws DBException;

    boolean checkAmountConstraint(LoanRequest loanRequest) throws DBException;
}

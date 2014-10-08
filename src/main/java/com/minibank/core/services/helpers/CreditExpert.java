package com.minibank.core.services.helpers;

import com.minibank.core.domain.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 12/09/14.
 */
@Component
public class CreditExpert
{
    @Autowired
    private ConstraintChecker checker;

    public boolean hasRisks(LoanRequest loanRequest)
    {
        if (!checker.checkMaxRequestsPerIP(loanRequest)||
            (checker.isMaxAmount(loanRequest)&&!checker.checkTimeConstraint(loanRequest)))
           return true;
        else
           return false;
    }
}

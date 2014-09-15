package com.minibank.core.services.helpers;

import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repository.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 12/09/14.
 */
@Component
public class CreditExpertImpl implements CreditExpert
{
    @Autowired
    private ConstraintChecker checker;

    @Override
    public boolean hasRisks(LoanRequest loanRequest) throws DBException
    {
        if (!checker.checkMaxRequestsPerIP(loanRequest)
               ||
                  (
                    !checker.checkAmountConstraint(loanRequest)

                    &&

                     !checker.checkTimeConstraint(loanRequest)
                  )
           )
           return true;
        else
           return false;
    }
}

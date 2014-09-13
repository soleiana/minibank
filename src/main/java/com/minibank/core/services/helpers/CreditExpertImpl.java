package com.minibank.core.services.helpers;

import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repository.DBException;
import com.minibank.core.services.helpers.CreditExpert;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 12/09/14.
 */
@Component
public class CreditExpertImpl implements CreditExpert
{
    @Override
    public boolean hasRisks(LoanRequest loanRequest) throws DBException
    {
        return false;
    }
}

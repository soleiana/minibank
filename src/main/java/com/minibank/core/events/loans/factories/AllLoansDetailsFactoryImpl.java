package com.minibank.core.events.loans.factories;

import com.minibank.core.domain.AllLoans;
import com.minibank.core.events.loans.domain.AllLoansDetails;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 14/09/14.
 */

@Component
public class AllLoansDetailsFactoryImpl implements AllLoansDetailsFactory
{
    @Override
    public AllLoansDetails getNewAllLoansDetails(AllLoans allLoans)
    {
        return null;
    }
}

package com.minibank.core.services.helpers;

import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repository.DBException;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Ann on 12/09/14.
 */
public interface CreditCalculator
{
    Date getLoanEndDate(LoanRequest loanRequest);

    BigDecimal getInterest(LoanRequest loanRequest) throws DBException;
}

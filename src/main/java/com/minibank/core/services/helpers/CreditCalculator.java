package com.minibank.core.services.helpers;

import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repositories.DBException;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Ann on 12/09/14.
 */
public interface CreditCalculator
{
    Date getLoanEndDate(LoanRequest loanRequest);

    Date getLoanExtensionEndDate(Loan loan) throws DBException;

    BigDecimal getInterest(LoanRequest loanRequest) throws DBException;

    BigDecimal getInterest(Loan loan) throws DBException;

    BigDecimal getNewInterestRate(Loan loan) throws DBException;
}

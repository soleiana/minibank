package com.minibank.rest.validators;

import com.minibank.rest.domain.LoanRequest;

/**
 * Created by Ann on 17/09/14.
 */
public interface LoanRequestValidator
{
    boolean validate(LoanRequest loanRequest);
}

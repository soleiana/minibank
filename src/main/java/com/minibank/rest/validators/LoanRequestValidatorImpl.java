package com.minibank.rest.validators;

import com.minibank.rest.domain.LoanRequest;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 17/09/14.
 */

@Component
public class LoanRequestValidatorImpl implements LoanRequestValidator
{
    @Override
    public boolean validate(LoanRequest loanRequest)
    {
        return true;
    }
}

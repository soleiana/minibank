package com.minibank.core.validators;

import com.minibank.core.common.AppParametersProvider;
import com.minibank.core.model.BankParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class LoanAmountValidator {

    @Autowired
    private AppParametersProvider parametersProvider;

    public boolean isValid(BigDecimal loanRequestAmount) {
        BankParameters bankParams = parametersProvider.getBankParameters();
        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        return loanRequestAmount.compareTo(maxLoanAmount) != 1;
    }
}

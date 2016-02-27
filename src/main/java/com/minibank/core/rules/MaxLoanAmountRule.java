package com.minibank.core.rules;

import com.minibank.core.common.AppParametersProvider;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MaxLoanAmountRule {

    @Autowired
    private AppParametersProvider parametersProvider;


    public boolean holdsTrue(LoanRequest loanRequest) {
        BankParameters bankParams = parametersProvider.getBankParameters();
        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        BigDecimal loanRequestAmount = loanRequest.getAmount();
        return loanRequestAmount.compareTo(maxLoanAmount) == 0;
    }
}

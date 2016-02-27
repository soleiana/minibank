package com.minibank.core.rules;

import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class MaxLoanAmountRule extends Rule {

    @Override
    public boolean holdsTrue(LoanRequest loanRequest) {
        BankParameters bankParams = getBankParameters();
        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        BigDecimal loanRequestAmount = loanRequest.getAmount();
        return loanRequestAmount.compareTo(maxLoanAmount) == 0;
    }
}

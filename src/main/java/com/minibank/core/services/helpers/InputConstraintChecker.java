package com.minibank.core.services.helpers;

import com.minibank.core.domain.BankParameters;
import com.minibank.core.domain.LoanRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class InputConstraintChecker extends ConstraintChecker {

    public boolean checkAmountConstraint(LoanRequest loanRequest) {
        BankParameters bankParams = getBankParameters();
        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        BigDecimal loanRequestAmount = loanRequest.getAmount();
        return loanRequestAmount.compareTo(maxLoanAmount) != 1;
    }
}

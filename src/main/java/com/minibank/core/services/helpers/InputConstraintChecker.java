package com.minibank.core.services.helpers;

import com.minibank.core.domain.BankParams;
import com.minibank.core.domain.LoanRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class InputConstraintChecker extends ConstraintChecker {

    public boolean checkAmountConstraint(LoanRequest loanRequest) {
        BankParams bankParams = getBankParams();
        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        BigDecimal reqAmount = loanRequest.getAmount();
        return reqAmount.compareTo(maxLoanAmount) != 1;
    }
}

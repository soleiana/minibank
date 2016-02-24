package com.minibank.core.services.helpers;

import com.minibank.core.domain.BankParameters;
import com.minibank.core.domain.LoanRequest;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class InputConstraintChecker extends ConstraintChecker {

    public boolean isEqualOrLessThanMaxLoanAmount(BigDecimal loanRequestAmount) {
        BankParameters bankParams = getBankParameters();
        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        return loanRequestAmount.compareTo(maxLoanAmount) != 1;
    }
}

package com.minibank.core.services.helpers;

import com.minibank.core.model.BankParameters;
import com.minibank.core.repositories.BankParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;


@Component
public class InputConstraintChecker {

    @Autowired
    private BankParametersRepository bankParametersRepository;

    public boolean isEqualOrLessThanMaxLoanAmount(BigDecimal loanRequestAmount) {
        BankParameters bankParams = bankParametersRepository.getCurrentBankParameters();
        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        return loanRequestAmount.compareTo(maxLoanAmount) != 1;
    }
}

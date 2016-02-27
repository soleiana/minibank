package com.minibank.core.calculators;

import com.minibank.core.common.AppParametersProvider;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;


@Component
public class LoanCalculator {

    @Autowired
    private AppParametersProvider parametersProvider;

    @Autowired
    private CalculationUtility calculationUtility;


    public LocalDate getLoanEndDate(LoanRequest loanRequest) {
        LocalDate startDate = loanRequest.getSubmissionDate();
        Integer term = loanRequest.getTerm();
        return startDate.plusDays(term);
    }

    public BigDecimal getInterest(LoanRequest loanRequest) {
        BankParameters bankParams = parametersProvider.getBankParameters();
        BigDecimal baseInterestRate = bankParams.getBaseInterestRate();
        BigDecimal amount = loanRequest.getAmount();
        BigDecimal term = new BigDecimal(loanRequest.getTerm());
        return calculationUtility.interestFormula(amount, term, baseInterestRate);
    }
}

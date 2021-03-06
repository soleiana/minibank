package com.minibank.core.calculators;

import com.minibank.core.common.AppParametersProvider;
import com.minibank.core.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

@Component
public class LoanExtensionCalculator {

    @Autowired
    private CalculationUtility calculationUtility;

    @Autowired
    private AppParametersProvider parametersProvider;

    public LocalDate getLoanExtensionEndDate(Loan loan) {
        LocalDate startDate = loan.getEndDate();
        short loanExtensionTerm = parametersProvider.getBankParameters().getLoanExtensionTerm();
        return startDate.plusDays(loanExtensionTerm);
    }

    public BigDecimal getInterest(Loan loan) {
        BigDecimal amount = loan.getAmount();
        BigDecimal currInterestRate = getNewInterestRate(loan.getCurrentInterestRate());
        short loanExtensionTerm = parametersProvider.getBankParameters().getLoanExtensionTerm();
        int term = loan.getTerm();
        term += loanExtensionTerm;
        return calculationUtility.interestFormula(amount, new BigDecimal(term), currInterestRate);
    }

    public BigDecimal getNewInterestRate(BigDecimal currentInterestRate) {
        BigDecimal interestRateFactor = parametersProvider.getBankParameters().getInterestRateFactor();
        currentInterestRate = currentInterestRate.multiply(interestRateFactor);
        return currentInterestRate.setScale(2, RoundingMode.HALF_EVEN);
    }

}

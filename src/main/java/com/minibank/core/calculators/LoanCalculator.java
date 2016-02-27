package com.minibank.core.calculators;

import com.minibank.core.common.AppParametersProvider;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


@Component
public class LoanCalculator {

    public static final BigDecimal FACTOR = new BigDecimal("0.0000277777");

    @Autowired
    private AppParametersProvider parametersProvider;

    @Autowired
    private CalculationUtility calculationUtility;


    public LocalDate getLoanEndDate(LoanRequest loanRequest) {
        LocalDate startDate = loanRequest.getSubmissionDate();
        Integer term = loanRequest.getTerm();
        return startDate.plusDays(term);
    }

    public LocalDate getLoanExtensionEndDate(Loan loan) {
        LocalDate startDate = loan.getEndDate();
        short loanExtensionTerm = parametersProvider.getBankParameters().getLoanExtensionTerm();
        return startDate.plusDays(loanExtensionTerm);
    }

    public BigDecimal getInterest(LoanRequest loanRequest) {
        BankParameters bankParams = parametersProvider.getBankParameters();
        BigDecimal baseInterestRate = bankParams.getBaseInterestRate();
        BigDecimal amount = loanRequest.getAmount();
        BigDecimal term = new BigDecimal(loanRequest.getTerm());
        return calculationUtility.interestFormula(amount,term,baseInterestRate);
    }

    public BigDecimal getInterest(Loan loan) {
       BigDecimal amount = loan.getAmount();
       BigDecimal currInterestRate = getNewInterestRate(loan);

       short loanExtensionTerm = parametersProvider.getBankParameters().getLoanExtensionTerm();
       int term = loan.getTerm();
       term += loanExtensionTerm;
       return calculationUtility.interestFormula(amount, new BigDecimal(term), currInterestRate);
    }

    public BigDecimal getNewInterestRate(Loan loan) {
        BankParameters bankParams = parametersProvider.getBankParameters();
        BigDecimal interestRateFactor = bankParams.getInterestRateFactor();
        BigDecimal currInterestRate = loan.getCurrInterestRate();
        currInterestRate = currInterestRate.multiply(interestRateFactor);
        currInterestRate = currInterestRate.setScale(2, RoundingMode.HALF_EVEN);
        return currInterestRate;
    }

}

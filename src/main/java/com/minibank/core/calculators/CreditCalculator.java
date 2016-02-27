package com.minibank.core.calculators;

import com.minibank.core.model.BankParameters;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.BankParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;


@Component
public class CreditCalculator {

    public static final BigDecimal FACTOR = new BigDecimal("0.0000277777");

    @Autowired
    private BankParametersRepository bankParametersRepository;


    public LocalDate getLoanEndDate(LoanRequest loanRequest) {
        LocalDate startDate = loanRequest.getSubmissionDate();
        Integer term = loanRequest.getTerm();
        return startDate.plusDays(term);
    }

    public LocalDate getLoanExtensionEndDate(Loan loan) {
        LocalDate startDate = loan.getEndDate();
        short loanExtensionTerm = getBankParameters().getLoanExtensionTerm();
        return startDate.plusDays(loanExtensionTerm);
    }

    public BigDecimal getInterest(LoanRequest loanRequest) {
        BankParameters bankParams = getBankParameters();
        BigDecimal baseInterestRate = bankParams.getBaseInterestRate();
        BigDecimal amount = loanRequest.getAmount();
        BigDecimal term = new BigDecimal(loanRequest.getTerm());
        return interestFormula(amount,term,baseInterestRate);
    }

    public BigDecimal getInterest(Loan loan) {
       BigDecimal amount = loan.getAmount();
       BigDecimal currInterestRate = getNewInterestRate(loan);

       short loanExtensionTerm = getBankParameters().getLoanExtensionTerm();
       int term = loan.getTerm();
       term += loanExtensionTerm;
       return interestFormula(amount, new BigDecimal(term), currInterestRate);
    }

    public BigDecimal getNewInterestRate(Loan loan) {
        BankParameters bankParams = bankParametersRepository.getCurrentBankParameters();
        BigDecimal interestRateFactor = bankParams.getInterestRateFactor();
        BigDecimal currInterestRate = loan.getCurrInterestRate();
        currInterestRate = currInterestRate.multiply(interestRateFactor);
        currInterestRate = currInterestRate.setScale(2, RoundingMode.HALF_EVEN);
        return currInterestRate;
    }

    private BigDecimal interestFormula(BigDecimal amount, BigDecimal term, BigDecimal interestRate) {
        BigDecimal factor = amount.multiply(interestRate).multiply(term);
        BigDecimal interest = factor.multiply(FACTOR);
        interest = interest.setScale(2, RoundingMode.HALF_EVEN);
        return interest;
    }

    private BankParameters getBankParameters() {
        return bankParametersRepository.getCurrentBankParameters();
    }
}

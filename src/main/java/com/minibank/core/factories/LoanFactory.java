package com.minibank.core.factories;

import com.minibank.core.calculators.LoanCalculator;
import com.minibank.core.common.AppParametersProvider;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanExtension;
import com.minibank.core.model.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;


@Component
public class LoanFactory {

    @Autowired
    private LoanCalculator loanCalculator;

    @Autowired
    private AppParametersProvider parametersProvider;


    public Loan getNewLoan(LoanRequest loanRequest) {
        Loan loan = new Loan();
        loan.setAmount(loanRequest.getAmount());
        loan.setTerm(loanRequest.getTerm());
        loan.setStartDate(loanRequest.getSubmissionDate());

        LocalDate endDate = loanCalculator.getLoanEndDate(loanRequest);
        loan.setEndDate(endDate);
        BigDecimal interest = loanCalculator.getInterest(loanRequest);
        loan.setCurrInterest(interest);

        BankParameters bankParams = parametersProvider.getBankParameters();
        loan.setCurrInterestRate(bankParams.getBaseInterestRate());
        return loan;
    }

    public Loan getExtendedLoan(Loan loan, LoanExtension loanExtension) {
        loan.setCurrInterestRate(loanExtension.getInterestRate());
        loan.setCurrInterest(loanExtension.getInterest());
        loan.setEndDate(loanExtension.getEndDate());
        return loan;
    }
}

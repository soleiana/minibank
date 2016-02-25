package com.minibank.core.services.factories;

import com.minibank.core.domain.*;
import com.minibank.core.repositories.BankParametersRepository;
import com.minibank.core.services.helpers.CreditCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;


@Component
public class LoanFactory {

    @Autowired
    private CreditCalculator creditCalculator;

    @Autowired
    private BankParametersRepository bankParametersRepository;


    public Loan getNewLoan(LoanRequest loanRequest) {
        Loan loan = new Loan();
        Customer customer = loanRequest.getCustomer();

        loan.setAmount(loanRequest.getAmount());
        loan.setTerm(loanRequest.getTerm());
        loan.setStartDate(loanRequest.getSubmissionDate());

        Date endDate = creditCalculator.getLoanEndDate(loanRequest);
        loan.setEndDate(endDate);
        BigDecimal interest = creditCalculator.getInterest(loanRequest);
        loan.setCurrInterest(interest);

        BankParameters bankParams = bankParametersRepository.getLast();
        loan.setCurrInterestRate(bankParams.getBaseInterestRate());

        customer.addLoan(loan);
        return loan;
    }

    public Loan getExtendedLoan(Loan loan, LoanExtension loanExtension) {
        loan.setCurrInterestRate(loanExtension.getInterestRate());
        loan.setCurrInterest(loanExtension.getInterest());
        loan.setEndDate(loanExtension.getEndDate());
        return loan;
    }
}

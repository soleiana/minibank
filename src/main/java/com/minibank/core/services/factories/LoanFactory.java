package com.minibank.core.services.factories;

import com.minibank.core.domain.BankParams;
import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repositories.BankParamsRepository;
import com.minibank.core.repositories.LoanRepository;
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
    private BankParamsRepository bankParamsRepository;

    @Autowired
    private LoanRepository loanRepository;

    public Loan getNewLoan(LoanRequest loanRequest) {
        Loan loan = new Loan();
        loan.setLoanRequest(loanRequest);
        loan.setCustomer(loanRequest.getCustomer());
        loan.setStartDate(loanRequest.getSubmissionDate());

        Date endDate = creditCalculator.getLoanEndDate(loanRequest);
        loan.setEndDate(endDate);
        BigDecimal interest = creditCalculator.getInterest(loanRequest);
        loan.setCurrInterest(interest);

        BankParams bankParams = bankParamsRepository.getLast();
        loan.setCurrInterestRate(bankParams.getBaseInterestRate());
        return loan;
    }

    public Loan getExtendedLoan(LoanExtension loanExtension) {
        Integer id = loanExtension.getLoan().getId();
        Loan loan = loanRepository.getById(id);
        loan.setCurrInterest(loanExtension.getInterestRate());
        loan.setCurrInterest(loanExtension.getInterest());
        loan.setEndDate(loanExtension.getEndDate());
        return loan;
    }
}

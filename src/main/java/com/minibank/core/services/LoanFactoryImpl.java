package com.minibank.core.services;

import com.minibank.core.domain.BankParams;
import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repository.BankParamsRepository;
import com.minibank.core.repository.DBException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;

/**
 * Created by Ann on 12/09/14.
 */
@Component
public class LoanFactoryImpl implements LoanFactory
{
    @Autowired
    private CreditCalculator creditCalculator;
    @Autowired
    private BankParamsRepository bankParamsRepository;

    @Override
    public Loan getNewLoan(LoanRequest loanRequest)throws DBException
    {
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

}

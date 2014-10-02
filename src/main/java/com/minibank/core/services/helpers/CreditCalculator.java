package com.minibank.core.services.helpers;

import com.minibank.core.domain.BankParams;
import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repositories.BankParamsRepository;
import com.minibank.core.services.common.Number;
import com.minibank.core.repositories.DBException;
import com.minibank.core.services.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.Date;


/**
 * Created by Ann on 12/09/14.
 */
@Component
public class CreditCalculator
{
    @Autowired
    private BankParamsRepository bankParamsRepository;

    private BigDecimal interestFormula(BigDecimal amount,
                                       BigDecimal term,
                                       BigDecimal interestRate)
    {
        BigDecimal factor = amount.multiply(interestRate)
                .multiply(term);

        BigDecimal interest = factor.multiply(Number.FACTOR);
        interest = interest.setScale(2, RoundingMode.HALF_EVEN);
        return interest;
    }

    public Date getLoanEndDate(LoanRequest loanRequest)
    {
        Date startDate = loanRequest.getSubmissionDate();
        Integer term = loanRequest.getTerm();

        Date endDate = DateTimeUtility.increaseDate(startDate,term);
        return endDate;
    }

    public Date getLoanExtensionEndDate(Loan loan) throws DBException
    {
        Date startDate = loan.getEndDate();
        short loanExtensionTerm = bankParamsRepository.getLast().getLoanExtensionTerm();
        Date endDate = DateTimeUtility.increaseDate(startDate, (int)loanExtensionTerm);
        return endDate;
    }

    public BigDecimal getInterest(LoanRequest loanRequest) throws DBException
    {
        BankParams bankParams = bankParamsRepository.getLast();
        BigDecimal baseInterestRate = bankParams.getBaseInterestRate();
        BigDecimal amount = loanRequest.getAmount();
        BigDecimal term = new BigDecimal(loanRequest.getTerm());
        return interestFormula(amount,term,baseInterestRate);
    }

    public BigDecimal getInterest(Loan loan) throws DBException
    {
       BigDecimal amount = loan.getLoanRequest().getAmount();
       BigDecimal currInterestRate = getNewInterestRate(loan);

       short loanExtensionTerm = bankParamsRepository.getLast().getLoanExtensionTerm();
       int term = loan.getLoanRequest().getTerm();
       term += loanExtensionTerm;
       return  interestFormula(amount, new BigDecimal(term),currInterestRate);
    }

    public BigDecimal getNewInterestRate(Loan loan) throws DBException
    {
        BankParams bankParams = bankParamsRepository.getLast();
        BigDecimal interestRateFactor = bankParams.getInterestRateFactor();
        BigDecimal currInterestRate = loan.getCurrInterestRate();
        currInterestRate = currInterestRate.multiply(interestRateFactor);
        currInterestRate = currInterestRate.setScale(2, RoundingMode.HALF_EVEN);
        return currInterestRate;
    }
}

package com.minibank.core.services.factories;

import com.minibank.core.domain.BankParams;
import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import com.minibank.core.events.loans.LoanExtensionDetails;
import com.minibank.core.repository.BankParamsRepository;
import com.minibank.core.repository.DBException;
import com.minibank.core.repository.LoanRepository;
import com.minibank.core.services.common.DateTimeUtility;
import com.minibank.core.services.helpers.CreditCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by Ann on 14/09/14.
 */
@Component
public class LoanExtensionFactoryImpl implements LoanExtensionFactory
{
    @Autowired
    private CreditCalculator creditCalculator;
    @Autowired
    private BankParamsRepository bankParamsRepository;
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public LoanExtension getNewLoanExtension(LoanExtensionDetails loanExtensionDetails)
            throws DBException
    {
        LoanExtension loanExtension = new LoanExtension();

        //We assume that customer already exists in DB
        Integer id = loanExtensionDetails.getLoanId();
        Loan loan = loanRepository.getById(id);

        loanExtension.setLoan(loan);

        Date dNow = new Date();
        java.sql.Date submissionDate = DateTimeUtility.getSqlDate(dNow);
        loanExtension.setSubmissionDate(submissionDate);

        loanExtension.setStartDate(loan.getEndDate());
        java.sql.Date endDate = creditCalculator.getLoanExtensionEndDate(loan);
        loanExtension.setEndDate(endDate);

        BigDecimal interestRate = creditCalculator.getNewInterestRate(loan);
        loanExtension.setInterestRate(interestRate);
        BigDecimal interest = creditCalculator.getInterest(loan);
        loanExtension.setInterest(interest);

        return  loanExtension;
    }
}

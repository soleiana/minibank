package com.minibank.core.services.factories;

import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanExtension;
import com.minibank.core.services.common.DateTimeParameters;
import com.minibank.core.services.helpers.CreditCalculator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;


@Component
public class LoanExtensionFactory {

    @Autowired
    private CreditCalculator creditCalculator;


    public LoanExtension getLoanExtension(Loan loan) {
        LoanExtension loanExtension = new LoanExtension();
        Date dNow = new Date();
        java.sql.Date submissionDate = DateTimeParameters.getSqlDate(dNow);
        loanExtension.setSubmissionDate(submissionDate);

        loanExtension.setStartDate(java.sql.Date.valueOf(loan.getEndDate()));
        java.sql.Date endDate = creditCalculator.getLoanExtensionEndDate(loan);
        loanExtension.setEndDate(endDate);

        BigDecimal interestRate = creditCalculator.getNewInterestRate(loan);
        loanExtension.setInterestRate(interestRate);
        BigDecimal interest = creditCalculator.getInterest(loan);
        loanExtension.setInterest(interest);
        return loanExtension;
    }
}

package com.minibank.core.factories;

import com.minibank.core.calculators.CreditCalculator;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;


@Component
public class LoanExtensionFactory {

    @Autowired
    private CreditCalculator creditCalculator;


    public LoanExtension getLoanExtension(Loan loan) {
        LoanExtension loanExtension = new LoanExtension();
        LocalDate now = LocalDate.now();
        loanExtension.setSubmissionDate(now);

        loanExtension.setStartDate(loan.getEndDate());
        LocalDate endDate = creditCalculator.getLoanExtensionEndDate(loan);
        loanExtension.setEndDate(endDate);

        BigDecimal interestRate = creditCalculator.getNewInterestRate(loan);
        loanExtension.setInterestRate(interestRate);
        BigDecimal interest = creditCalculator.getInterest(loan);
        loanExtension.setInterest(interest);
        return loanExtension;
    }
}

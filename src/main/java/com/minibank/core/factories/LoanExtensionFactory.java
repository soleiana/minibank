package com.minibank.core.factories;

import com.minibank.core.calculators.LoanExtensionCalculator;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;


@Component
public class LoanExtensionFactory {

    @Autowired
    private LoanExtensionCalculator loanExtensionCalculator;


    public LoanExtension getLoanExtension(Loan loan) {
        LoanExtension loanExtension = new LoanExtension();
        LocalDate now = LocalDate.now();
        loanExtension.setSubmissionDate(now);

        loanExtension.setStartDate(loan.getEndDate());
        LocalDate endDate = loanExtensionCalculator.getLoanExtensionEndDate(loan);
        loanExtension.setEndDate(endDate);

        BigDecimal interestRate = loanExtensionCalculator.getNewInterestRate(loan.getCurrInterestRate());
        loanExtension.setInterestRate(interestRate);
        BigDecimal interest = loanExtensionCalculator.getInterest(loan);
        loanExtension.setInterest(interest);
        return loanExtension;
    }
}

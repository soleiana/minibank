package com.minibank.communications.factories;

import com.minibank.communications.model.Loan;
import com.minibank.communications.model.LoanExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoanFactory {

    @Autowired
    private LoanExtensionFactory loanExtensionFactory;

    public List<Loan> getLoans(List<com.minibank.core.model.Loan> fromLoans) {
        return fromLoans.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private Loan convert(com.minibank.core.model.Loan fromLoan) {
        Loan toLoan = new Loan();
        toLoan.setId(fromLoan.getId());
        toLoan.setCurrInterestRate(fromLoan.getCurrInterestRate());
        toLoan.setCurrInterest(fromLoan.getCurrInterest());
        toLoan.setAmount(fromLoan.getAmount());
        toLoan.setStartDate(fromLoan.getStartDate());
        toLoan.setEndDate(fromLoan.getEndDate());
        List<LoanExtension> loanExtensions = loanExtensionFactory.getLoanExtensions(fromLoan.getLoanExtensions());
        toLoan.setLoanExtensions(loanExtensions);
        return toLoan;
    }
}

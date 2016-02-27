package com.minibank.communications.factories;

import com.minibank.communications.model.AllLoansDetails;
import com.minibank.communications.model.Loan;
import com.minibank.communications.model.LoanExtension;
import com.minibank.core.model.AllLoans;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class AllLoansDetailsFactory {

    public AllLoansDetails getAllLoansDetails(AllLoans allLoans) {
        AllLoansDetails allLoansDetails = new AllLoansDetails();
        allLoansDetails.setCustomerId(allLoans.getCustomer().getId());
        allLoansDetails.setName(allLoans.getCustomer().getName());
        allLoansDetails.setSurname(allLoans.getCustomer().getSurname());

        List<Loan> toLoans = getLoans(allLoans.getLoans());
        allLoansDetails.setLoans(toLoans);
        return allLoansDetails;
    }

    private List<Loan> getLoans(List<com.minibank.core.model.Loan> fromLoans) {
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
        List<LoanExtension> loanExtensions = getLoanExtensions(fromLoan.getLoanExtensions());
        toLoan.setLoanExtensions(loanExtensions);
        return toLoan;
    }

    private List<LoanExtension> getLoanExtensions(List<com.minibank.core.model.LoanExtension> fromLoanExtensions) {
        return fromLoanExtensions.stream()
                .map(this::convert)
                .collect(Collectors.toList());
    }

    private LoanExtension convert(com.minibank.core.model.LoanExtension fromLoanExtension) {
        LoanExtension toLoanExtension = new LoanExtension();
        toLoanExtension.setInterestRate(fromLoanExtension.getInterestRate());
        toLoanExtension.setInterest(fromLoanExtension.getInterest());
        toLoanExtension.setStartDate(fromLoanExtension.getStartDate());
        toLoanExtension.setEndDate(fromLoanExtension.getEndDate());
        toLoanExtension.setSubmissionDate(fromLoanExtension.getSubmissionDate());
        return toLoanExtension;
    }
}

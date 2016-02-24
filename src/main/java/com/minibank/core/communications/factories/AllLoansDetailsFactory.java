package com.minibank.core.communications.factories;

import com.minibank.core.domain.AllLoans;
import com.minibank.core.communications.domain.LoanExtension;
import com.minibank.core.communications.domain.AllLoansDetails;
import com.minibank.core.communications.domain.Loan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AllLoansDetailsFactory {

    public AllLoansDetails getNewAllLoansDetails(AllLoans allLoans) {
        AllLoansDetails allLoansDetails = new AllLoansDetails();
        allLoansDetails.setCustomerId(allLoans.getCustomer().getId());
        allLoansDetails.setName(allLoans.getCustomer().getName());
        allLoansDetails.setSurname(allLoans.getCustomer().getSurname());

        List<Loan> toLoans = new ArrayList<>();
        for(com.minibank.core.domain.Loan fromLoan: allLoans.getLoans()) {
            Loan toLoan = convert(fromLoan);
            List<LoanExtension> toLoanExtensions = new ArrayList<>();
            for(com.minibank.core.domain.LoanExtension fromLoanExtension: fromLoan.getLoanExtensions()) {
                    LoanExtension toLoanExtension = convert(fromLoanExtension);
                    toLoanExtensions.add(toLoanExtension);
            }
            toLoan.setLoanExtensions(toLoanExtensions);
            toLoans.add(toLoan);
        }
        allLoansDetails.setLoans(toLoans);
        return allLoansDetails;
    }

    private Loan convert(com.minibank.core.domain.Loan fromLoan) {
        Loan toLoan = new Loan();
        toLoan.setId(fromLoan.getId());
        toLoan.setCurrInterestRate(fromLoan.getCurrInterestRate());
        toLoan.setCurrInterest(fromLoan.getCurrInterest());
        toLoan.setAmount(fromLoan.getLoanRequest().getAmount());
        toLoan.setStartDate(fromLoan.getStartDate());
        toLoan.setEndDate(fromLoan.getEndDate());
        return toLoan;
    }

    private LoanExtension convert(com.minibank.core.domain.LoanExtension fromLoanExtension) {
        LoanExtension toLoanExtension = new LoanExtension();
        toLoanExtension.setInterestRate(fromLoanExtension.getInterestRate());
        toLoanExtension.setInterest(fromLoanExtension.getInterest());
        toLoanExtension.setStartDate(fromLoanExtension.getStartDate());
        toLoanExtension.setEndDate(fromLoanExtension.getEndDate());
        toLoanExtension.setSubmissionDate(fromLoanExtension.getSubmissionDate());
        return toLoanExtension;
    }
}

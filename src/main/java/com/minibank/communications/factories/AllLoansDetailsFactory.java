package com.minibank.communications.factories;

import com.minibank.core.model.AllLoans;
import com.minibank.communications.model.LoanExtension;
import com.minibank.communications.model.AllLoansDetails;
import com.minibank.communications.model.Loan;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class AllLoansDetailsFactory {

    public AllLoansDetails getAllLoansDetails(AllLoans allLoans) {
        AllLoansDetails allLoansDetails = new AllLoansDetails();
        allLoansDetails.setCustomerId(allLoans.getCustomer().getId());
        allLoansDetails.setName(allLoans.getCustomer().getName());
        allLoansDetails.setSurname(allLoans.getCustomer().getSurname());

        List<Loan> toLoans = new ArrayList<>();
        for(com.minibank.core.model.Loan fromLoan: allLoans.getLoans()) {
            Loan toLoan = convert(fromLoan);
            List<LoanExtension> toLoanExtensions = new ArrayList<>();
            for(com.minibank.core.model.LoanExtension fromLoanExtension: fromLoan.getLoanExtensions()) {
                    LoanExtension toLoanExtension = convert(fromLoanExtension);
                    toLoanExtensions.add(toLoanExtension);
            }
            toLoan.setLoanExtensions(toLoanExtensions);
            toLoans.add(toLoan);
        }
        allLoansDetails.setLoans(toLoans);
        return allLoansDetails;
    }

    private Loan convert(com.minibank.core.model.Loan fromLoan) {
        Loan toLoan = new Loan();
        toLoan.setId(fromLoan.getId());
        toLoan.setCurrInterestRate(fromLoan.getCurrInterestRate());
        toLoan.setCurrInterest(fromLoan.getCurrInterest());
        toLoan.setAmount(fromLoan.getAmount());
        toLoan.setStartDate(fromLoan.getStartDate());
        toLoan.setEndDate(fromLoan.getEndDate());
        return toLoan;
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

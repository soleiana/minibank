package com.minibank.rest.factories;

import com.minibank.communications.model.AllLoansDetails;
import com.minibank.communications.model.Customer;
import com.minibank.rest.model.AllLoans;
import com.minibank.rest.model.Loan;
import com.minibank.rest.model.LoanExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AllLoansRestFactory {

    @Autowired
    private LoanRestFactory loanRestFactory;

    @Autowired
    private CustomerRestFactory customerRestFactory;


    public  AllLoans getAllLoans(AllLoansDetails allLoansDetails) {
        AllLoans allLoans = new AllLoans();
        allLoans.setCustomerId(allLoansDetails.getCustomerId());
        allLoans.setName(allLoansDetails.getName());
        allLoans.setSurname(allLoansDetails.getSurname());

        Customer customer = allLoansDetails.getCustomer();
        allLoans.setCustomer(customerRestFactory.getCustomer(customer));

        List<Loan> toLoans = loanRestFactory.getLoans(allLoansDetails.getLoans());
        allLoans.setLoans(toLoans);
        return allLoans;
    }

    private Loan convert(com.minibank.communications.model.Loan fromLoan) {
        Loan toLoan = new Loan();
        toLoan.setId(fromLoan.getId());
        toLoan.setCurrInterestRate(fromLoan.getCurrInterestRate());
        toLoan.setCurrInterest(fromLoan.getCurrInterest());
        toLoan.setAmount(fromLoan.getAmount());
        toLoan.setStartDate(fromLoan.getStartDate());
        toLoan.setEndDate(fromLoan.getEndDate());
        return toLoan;
    }

    private LoanExtension convert(com.minibank.communications.model.LoanExtension fromLoanExtension) {
        LoanExtension toLoanExtension = new LoanExtension();
        toLoanExtension.setInterestRate(fromLoanExtension.getInterestRate());
        toLoanExtension.setInterest(fromLoanExtension.getInterest());
        toLoanExtension.setStartDate(fromLoanExtension.getStartDate());
        toLoanExtension.setEndDate(fromLoanExtension.getEndDate());
        toLoanExtension.setSubmissionDate(fromLoanExtension.getSubmissionDate());
        return toLoanExtension;
    }
}

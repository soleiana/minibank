package com.minibank.communications.fixtures;

import com.minibank.communications.model.AllLoansDetails;
import com.minibank.communications.model.Customer;
import com.minibank.communications.model.Loan;
import com.minibank.communications.model.LoanExtension;

import java.util.ArrayList;
import java.util.List;


public class AllLoanDetailsFixture {

    public static AllLoansDetails standardAllLoansDetails() {
        AllLoansDetails allLoansDetails = new AllLoansDetails();
        Customer customer = CustomerFixture.standardCustomer();
        Loan loan = LoanFixture.standardLoan();
        LoanExtension loanExtension = LoanExtensionFixture.standardLoanExtension();
        List<LoanExtension> loanExtensions = new ArrayList<>();
        loanExtensions.add(loanExtension);
        loan.setLoanExtensions(loanExtensions);
        List<Loan> loans = new ArrayList<>();
        loans.add(loan);
        allLoansDetails.setLoans(loans);
        allLoansDetails.setCustomer(customer);
        return allLoansDetails;
    }
}

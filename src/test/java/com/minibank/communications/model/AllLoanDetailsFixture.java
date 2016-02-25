package com.minibank.communications.model;

import com.minibank.core.model.Customer;
import com.minibank.core.model.CustomerFixture;

import java.util.ArrayList;
import java.util.List;


public class AllLoanDetailsFixture {

    private static final Integer CUSTOMER_ID = 1;

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
        allLoansDetails.setCustomerId(CUSTOMER_ID);
        allLoansDetails.setName(customer.getName());
        allLoansDetails.setSurname(customer.getSurname());
        return  allLoansDetails;
    }
}

package com.minibank.core.model;

import java.util.ArrayList;
import java.util.List;


public class AllLoansFixture {

    public static AllLoans standardAllLoans() {
        AllLoans allLoans = new AllLoans();

        Loan loan = LoanFixture.standardLoan();
        LoanExtension loanExtension = LoanExtensionFixture.standardLoanExtension();
        Customer customer = CustomerFixture.standardCustomer();
        loan.addLoanExtension(loanExtension);
        List<Loan> loans = new ArrayList<>();
        loans.add(loan);
        allLoans.setLoans(loans);
        allLoans.setCustomer(customer);
        return allLoans;
    }
}

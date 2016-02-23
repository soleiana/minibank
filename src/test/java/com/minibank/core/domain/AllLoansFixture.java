package com.minibank.core.domain;

import java.util.ArrayList;
import java.util.List;


public class AllLoansFixture {

    public static AllLoans standardAllLoans() {
        AllLoans allLoans = new AllLoans();

        Loan loan = LoanFixture.standardLoan();
        LoanExtension loanExtension = LoanExtensionFixture.standardLoanExtension();
        Customer customer = CustomerFixture.standardCustomer();

        List<LoanExtension> loanExtensions = new ArrayList<>();
        loanExtensions.add(loanExtension);
        loan.setLoanExtensions(loanExtensions);
        List<Loan> loans = new ArrayList<>();
        loans.add(loan);
        allLoans.setLoans(loans);
        allLoans.setCustomer(customer);
        return allLoans;
    }
}

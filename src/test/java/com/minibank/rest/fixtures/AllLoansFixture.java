package com.minibank.rest.fixtures;

import com.minibank.rest.model.AllLoans;
import com.minibank.rest.model.Customer;
import com.minibank.rest.model.Loan;
import com.minibank.rest.model.LoanExtension;

import java.util.ArrayList;
import java.util.List;


public class AllLoansFixture {

    public static AllLoans standardAllLoans() {
        AllLoans allLoans = new AllLoans();
        Customer customer = CustomerFixture.standardCustomer();
        allLoans.setCustomer(customer);

        Loan loan = LoanFixture.standardLoan();
        List<LoanExtension> loanExtensions = new ArrayList<>();
        loanExtensions.add(LoanExtensionFixture.standardLoanExtension());
        loan.setLoanExtensions(loanExtensions);

        List<Loan> loans = new ArrayList<>();
        loans.add(loan);
        allLoans.setLoans(loans);
        return allLoans;
    }

    public static AllLoans emptyAllLoans() {
        AllLoans allLoans = new AllLoans();
        Customer customer = CustomerFixture.standardCustomer();
        allLoans.setCustomer(customer);
        return allLoans;
    }
}

package com.minibank.core.fixtures;

import com.minibank.core.model.AllLoans;
import com.minibank.core.model.Customer;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanExtension;

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

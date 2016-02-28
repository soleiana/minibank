package com.minibank.communications.factories;

import com.minibank.communications.model.AllLoansDetails;
import com.minibank.communications.model.Customer;
import com.minibank.communications.model.Loan;
import com.minibank.core.model.AllLoans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AllLoansDetailsFactory {

    @Autowired
    private CustomerFactory customerFactory;

    @Autowired
    private LoanFactory loanFactory;

    public AllLoansDetails getAllLoansDetails(AllLoans allLoans) {
        AllLoansDetails allLoansDetails = new AllLoansDetails();
        allLoansDetails.setCustomerId(allLoans.getCustomer().getId());
        allLoansDetails.setName(allLoans.getCustomer().getName());
        allLoansDetails.setSurname(allLoans.getCustomer().getSurname());
        Customer customer = customerFactory.getCustomer(allLoans.getCustomer());
        allLoansDetails.setCustomer(customer);

        List<Loan> toLoans = loanFactory.getLoans(allLoans.getLoans());
        allLoansDetails.setLoans(toLoans);
        return allLoansDetails;
    }
}

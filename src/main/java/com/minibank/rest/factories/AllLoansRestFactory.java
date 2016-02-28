package com.minibank.rest.factories;

import com.minibank.communications.model.AllLoansDetails;
import com.minibank.communications.model.Customer;
import com.minibank.rest.model.AllLoans;
import com.minibank.rest.model.Loan;
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
        Customer customer = allLoansDetails.getCustomer();
        allLoans.setCustomer(customerRestFactory.getCustomer(customer));
        List<Loan> toLoans = loanRestFactory.getLoans(allLoansDetails.getLoans());
        allLoans.setLoans(toLoans);
        return allLoans;
    }
}

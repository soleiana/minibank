package com.minibank.core.services.factories;

import com.minibank.core.domain.AllLoans;
import com.minibank.core.domain.Customer;
import com.minibank.core.domain.Loan;
import com.minibank.core.repositories.CustomerRepository;
import com.minibank.core.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class AllLoansCoreFactory {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    LoanRepository loanRepository;


    public AllLoans getAllLoans(Integer customerId) {
        AllLoans allLoans = new AllLoans();
        Customer customer = customerRepository.getById(customerId);
        allLoans.setCustomer(customer);
        List<Loan> loans = loanRepository.getByCustomer(customer);
        allLoans.setLoans(loans);
        return allLoans;
    }
}

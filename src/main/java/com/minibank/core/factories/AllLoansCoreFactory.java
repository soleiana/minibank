package com.minibank.core.factories;

import com.minibank.core.model.AllLoans;
import com.minibank.core.model.Customer;
import com.minibank.core.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class AllLoansCoreFactory {

    @Autowired
    CustomerRepository customerRepository;

    public AllLoans getAllLoans(Integer customerId) {
        AllLoans allLoans = new AllLoans();
        Customer customer = customerRepository.getById(customerId);
        allLoans.setCustomer(customer);
        allLoans.setLoans(customer.getLoans());
        return allLoans;
    }
}

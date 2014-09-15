package com.minibank.core.services.factories;

import com.minibank.core.domain.AllLoans;
import com.minibank.core.domain.Customer;
import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import com.minibank.core.events.loans.domain.RequestAllLoansDetails;
import com.minibank.core.repository.CustomerRepository;
import com.minibank.core.repository.DBException;
import com.minibank.core.repository.LoanExtensionRepository;
import com.minibank.core.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ann on 14/09/14.
 */
@Component
public class AllLoansFactoryImpl implements AllLoansFactory
{
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    LoanRepository loanRepository;
    @Autowired
    LoanExtensionRepository loanExtensionRepository;

    @Override
    public AllLoans getNewAllLoans(RequestAllLoansDetails requestAllLoansDetails)
            throws DBException
    {
        AllLoans allLoans = new AllLoans();
        Integer customerId = requestAllLoansDetails.getCustomerId();
        Customer customer = customerRepository.getById(customerId);
        allLoans.setCustomer(customer);
        List<Loan> loans = loanRepository.getByCustomer(customer);
        for(Loan loan: loans)
            {
                List<LoanExtension> loanExtensions = loanExtensionRepository.getByLoan(loan);
                loan.setLoanExtensions(loanExtensions);
            }
        allLoans.setLoans(loans);
        return  allLoans;
    }
}
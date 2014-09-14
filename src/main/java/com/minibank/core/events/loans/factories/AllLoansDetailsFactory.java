package com.minibank.core.events.loans.factories;

import com.minibank.core.domain.Customer;
import com.minibank.core.domain.Loan;
import com.minibank.core.events.loans.domain.AllLoansDetails;

import java.util.List;

/**
 * Created by Ann on 14/09/14.
 */
public interface AllLoansDetailsFactory
{
    AllLoansDetails getNewAllLoansDetails(List<Loan> loans, Customer customer);
}

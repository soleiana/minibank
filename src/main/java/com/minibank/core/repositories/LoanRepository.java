package com.minibank.core.repositories;

import com.minibank.core.domain.Customer;
import com.minibank.core.domain.Loan;

import java.util.List;

/**
 * Created by Ann on 06/09/14.
 */
public interface LoanRepository
{
    void create(Loan loan);

    void update(Loan loan);

    Loan getById(Integer id);

    List<Loan> getByCustomer(Customer customer);

    Loan getLast();
}

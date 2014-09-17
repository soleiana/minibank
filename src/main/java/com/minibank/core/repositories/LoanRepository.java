package com.minibank.core.repositories;

import com.minibank.core.domain.Customer;
import com.minibank.core.domain.Loan;

import java.util.List;

/**
 * Created by Ann on 06/09/14.
 */
public interface LoanRepository
{
    void create(Loan loan) throws  DBException;

    void update(Loan loan) throws DBException;

    Loan getById(Integer id) throws DBException;

    List<Loan> getByCustomer(Customer customer) throws DBException;

    Loan getLast() throws DBException;
}

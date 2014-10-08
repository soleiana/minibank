package com.minibank.core.repositories;

import com.minibank.core.domain.Customer;

/**
 * Created by Ann on 06/09/14.
 */
public interface CustomerRepository
{
    void create(Customer customer);

    Customer getById(Integer id);
}

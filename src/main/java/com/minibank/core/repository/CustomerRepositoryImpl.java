package com.minibank.core.repository;

import com.minibank.core.domain.Customer;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 10/09/14.
 */
@Component
public class CustomerRepositoryImpl extends SessionProvider
    implements CustomerRepository
{
    @Override
    public void create(Customer customer) throws DBException
    {
        getCurrentSession().saveOrUpdate(customer);
    }

    @Override
    public Customer getById(Integer id) throws DBException
    {
        Session session = getCurrentSession();
        return (Customer) session.get(Customer.class, id);
    }
}
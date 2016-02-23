package com.minibank.core.repositories;

import com.minibank.core.domain.Customer;
import org.hibernate.Session;
import org.springframework.stereotype.Component;


@Component
public class CustomerRepositoryImpl extends SessionProvider implements CustomerRepository {

    @Override
    public void create(Customer customer) {
        getCurrentSession().saveOrUpdate(customer);
    }

    @Override
    public Customer getById(Integer id) {
        Session session = getCurrentSession();
        return (Customer) session.get(Customer.class, id);
    }
}

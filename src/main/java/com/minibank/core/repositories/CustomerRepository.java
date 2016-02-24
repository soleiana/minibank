package com.minibank.core.repositories;

import com.minibank.core.domain.Customer;
import org.hibernate.Session;
import org.springframework.stereotype.Component;


@Component
public class CustomerRepository extends SessionProvider {

    public void create(Customer customer) {
        getCurrentSession().saveOrUpdate(customer);
    }

    public Customer getById(Integer id) {
        Session session = getCurrentSession();
        return (Customer) session.get(Customer.class, id);
    }
}

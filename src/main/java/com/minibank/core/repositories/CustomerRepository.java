package com.minibank.core.repositories;

import com.minibank.core.model.Customer;
import org.hibernate.Session;
import org.springframework.stereotype.Component;


@Component
public class CustomerRepository extends SessionProvider {

    public Customer getById(Integer id) {
        Session session = getCurrentSession();
        return session.get(Customer.class, id);
    }
}

package com.minibank.core.repositories;

import com.minibank.core.model.Customer;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CustomerRepository extends SessionProvider {

    @Autowired
    private RepositoryTemplateMethod<Customer> repositoryTemplateMethod;

    public void create(Customer customer) {
        getCurrentSession().saveOrUpdate(customer);
    }

    public Customer getById(Integer id) {
        Session session = getCurrentSession();
        return session.get(Customer.class, id);
    }

    public Customer getLast() {
        return repositoryTemplateMethod.getLast(Customer.class);
    }
}

package com.minibank.testutil.repositories;

import com.minibank.core.model.Customer;
import com.minibank.core.repositories.RepositoryTemplateMethod;
import com.minibank.core.repositories.SessionProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestCustomerRepository extends SessionProvider {

    @Autowired
    private RepositoryTemplateMethod<Customer> repositoryTemplateMethod;

    public void create(Customer customer) {
        getCurrentSession().saveOrUpdate(customer);
    }

    public Customer getLast() {
        return repositoryTemplateMethod.getLast(Customer.class);
    }
}

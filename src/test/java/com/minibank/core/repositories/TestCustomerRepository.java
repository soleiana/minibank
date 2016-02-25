package com.minibank.core.repositories;

import com.minibank.core.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class TestCustomerRepository extends SessionProvider {

    public void create(Customer customer) {
        getCurrentSession().saveOrUpdate(customer);
    }

}

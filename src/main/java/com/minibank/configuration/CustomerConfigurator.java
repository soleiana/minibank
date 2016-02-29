package com.minibank.configuration;

import com.minibank.core.model.Customer;
import com.minibank.core.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@PropertySource({ "classpath:test_customer.properties" })
public class CustomerConfigurator {

    @Autowired
    private Environment env;

    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    public int persistCustomer() {
        Customer customer = getCustomer();
        customerRepository.create(customer);
        return customer.getId();
    }

    private Customer getCustomer() {
        Customer customer = new Customer();
        customer.setName(env.getProperty("name", String.class));
        customer.setSurname(env.getProperty("surname", String.class));
        return customer;
    }
}

package com.minibank.communications.factories;

import com.minibank.communications.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerFactory {

    public Customer getCustomer(com.minibank.core.model.Customer fromCustomer) {
        Customer toCustomer = new Customer();
        toCustomer.setId(fromCustomer.getId());
        toCustomer.setName(fromCustomer.getName());
        toCustomer.setSurname(fromCustomer.getSurname());
        return toCustomer;
    }
}

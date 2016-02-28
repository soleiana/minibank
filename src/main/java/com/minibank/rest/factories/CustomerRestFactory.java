package com.minibank.rest.factories;

import com.minibank.rest.model.Customer;
import org.springframework.stereotype.Component;

@Component
public class CustomerRestFactory {

    public Customer getCustomer(com.minibank.communications.model.Customer fromCustomer) {
        Customer toCustomer = new Customer();
        toCustomer.setId(fromCustomer.getId());
        toCustomer.setName(fromCustomer.getName());
        toCustomer.setSurname(fromCustomer.getSurname());
        return toCustomer;
    }
}

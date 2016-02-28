package com.minibank.rest.factories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoanRestFactory {

    @Autowired
    private LoanExtensionRestFactory loanExtensionRestFactory;

}

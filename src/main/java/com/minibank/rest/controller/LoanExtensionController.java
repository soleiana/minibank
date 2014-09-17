package com.minibank.rest.controller;

import com.minibank.core.events.loans.CreateLoanExtensionEvent;
import com.minibank.core.events.loans.LoanExtensionCreatedEvent;
import com.minibank.core.services.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Ann on 06/09/14.
 */
@Controller
@RequestMapping("/rest/loans/loanExtensions")
public class LoanExtensionController
{
    @Autowired
    private LoanService loanService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createLoanExtension(@RequestBody Integer id)
    {
        CreateLoanExtensionEvent createLoanExtensionEvent =
                new CreateLoanExtensionEvent(id);
        LoanExtensionCreatedEvent loanExtensionCreatedEvent =
                loanService.createLoanExtension(createLoanExtensionEvent);
        String message = loanExtensionCreatedEvent.getMessage();

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}

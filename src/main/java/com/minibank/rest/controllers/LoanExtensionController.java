package com.minibank.rest.controllers;

import com.minibank.core.events.loans.CreateLoanExtensionEvent;
import com.minibank.core.events.loans.LoanExtensionCreatedEvent;
import com.minibank.core.services.LoanService;
import com.minibank.rest.common.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Ann on 06/09/14.
 */
@Controller
@RequestMapping("/rest/loans/loanExtensions")
public class LoanExtensionController
{
    @Autowired
    private LoanService loanService;

    private boolean validate(Integer loanId)
    {
        if (loanId == null)
            return  false;
        if (loanId <= 0)
            return  false;
        return  true;
    }

    @RequestMapping(method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces({MediaType.TEXT_HTML})
    public ResponseEntity<String> createLoanExtension(@RequestBody Integer id)
    {
        if (!validate(id))
            return new ResponseEntity<>(Message.INVALID_INPUT_FORMAT,
                    HttpStatus.FORBIDDEN);

        CreateLoanExtensionEvent createLoanExtensionEvent =
                new CreateLoanExtensionEvent(id);
        LoanExtensionCreatedEvent loanExtensionCreatedEvent =
                loanService.createLoanExtension(createLoanExtensionEvent);
        String message = loanExtensionCreatedEvent.getMessage();

        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}

package com.minibank.rest.controllers;

import com.minibank.core.events.loans.CreateLoanEvent;
import com.minibank.core.events.loans.LoanCreatedEvent;
import com.minibank.core.events.loans.domain.LoanRequestDetails;
import com.minibank.core.events.loans.factories.LoanRequestDetailsFactory;
import com.minibank.core.services.LoanService;
import com.minibank.rest.common.Message;
import com.minibank.rest.domain.LoanRequest;
import com.minibank.rest.validators.LoanRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

/**
 * Created by Ann on 06/09/14.
 */

@Controller
@RequestMapping("/rest/loans")
public class LoanController
{
    @Autowired
    private LoanRequestValidator loanRequestValidator;

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanRequestDetailsFactory loanRequestDetailsFactory;


    @RequestMapping(method = RequestMethod.POST)
    @Consumes(MediaType.APPLICATION_JSON)
    public ResponseEntity<String> createLoan(@RequestBody LoanRequest loanRequest,
                                             HttpServletRequest httpServletRequest)
    {
        if (!loanRequestValidator.validate(loanRequest))
            return new ResponseEntity<>(Message.INVALID_INPUT_FORMAT,HttpStatus.FORBIDDEN);

        String ip = httpServletRequest.getRemoteAddr();
        loanRequest.setRequestIP(ip);
        LoanRequestDetails loanRequestDetails = loanRequestDetailsFactory.getNewLoanRequestDetails(loanRequest);
        LoanCreatedEvent loanCreatedEvent = loanService.createLoan(new CreateLoanEvent(loanRequestDetails));

        String message = loanCreatedEvent.getMessage();

        if(loanCreatedEvent.isLoanObtained())
           return  new ResponseEntity<>(message, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

}

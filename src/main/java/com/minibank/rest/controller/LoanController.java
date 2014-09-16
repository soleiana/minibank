package com.minibank.rest.controller;

import com.minibank.core.events.loans.CreateLoanEvent;
import com.minibank.core.events.loans.LoanCreatedEvent;
import com.minibank.core.events.loans.domain.LoanRequestDetails;
import com.minibank.core.events.loans.factories.LoanRequestDetailsFactory;
import com.minibank.core.services.LoanService;
import com.minibank.rest.domain.LoanRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Created by Ann on 06/09/14.
 */

@Controller
@RequestMapping("/rest/loans")
public class LoanController
{
    private  static Logger log = LoggerFactory.getLogger(LoanController.class);

    @Autowired
    private LoanService loanService;

    @Autowired
    private LoanRequestDetailsFactory loanRequestDetailsFactory;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<String> createLoan(@RequestBody LoanRequest loanRequest)
    {
        LoanRequestDetails loanRequestDetails = loanRequestDetailsFactory.getNewLoanRequestDetails(loanRequest);
        LoanCreatedEvent loanCreatedEvent = loanService.createLoan(new CreateLoanEvent(loanRequestDetails));

        String message = loanCreatedEvent.getMessage();

        if(loanCreatedEvent.isLoanObtained())
           return  new ResponseEntity<>(message, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(message, HttpStatus.FORBIDDEN);
    }

}

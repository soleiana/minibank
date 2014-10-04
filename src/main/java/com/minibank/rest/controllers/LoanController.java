package com.minibank.rest.controllers;

import com.minibank.core.communications.loans.CreateLoanQuery;
import com.minibank.core.communications.loans.CreateLoanResponse;
import com.minibank.core.communications.loans.domain.LoanRequestDetails;
import com.minibank.core.communications.loans.factories.LoanRequestDetailsFactory;
import com.minibank.core.services.QueryExecutor;
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

/**
 * Created by Ann on 06/09/14.
 */

@Controller
@RequestMapping("/loans")
public class LoanController
{
    @Autowired
    private LoanRequestValidator loanRequestValidator;

    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private LoanRequestDetailsFactory loanRequestDetailsFactory;


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> createLoan(@RequestBody LoanRequest loanRequest,
                                             HttpServletRequest httpServletRequest)
    {
        if (!loanRequestValidator.validate(loanRequest))
            return new ResponseEntity<>(Message.INVALID_INPUT_FORMAT,HttpStatus.BAD_REQUEST);

        String ip = httpServletRequest.getRemoteAddr();
        loanRequest.setRequestIP(ip);
        LoanRequestDetails loanRequestDetails = loanRequestDetailsFactory.getNewLoanRequestDetails(loanRequest);

        CreateLoanResponse createLoanResponse = queryExecutor.execute(new CreateLoanQuery(loanRequestDetails));

        String message = createLoanResponse.getMessage();

        if(createLoanResponse.isCreated())
           return  new ResponseEntity<>(message, HttpStatus.CREATED);
        else
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

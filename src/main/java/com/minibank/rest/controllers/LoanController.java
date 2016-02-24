package com.minibank.rest.controllers;

import com.minibank.core.communications.CreateLoanQuery;
import com.minibank.core.communications.CreateLoanResponse;
import com.minibank.core.communications.domain.LoanRequestDetails;
import com.minibank.core.communications.factories.LoanRequestDetailsFactory;
import com.minibank.core.services.CreateLoanQueryHandler;
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


@Controller
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanRequestValidator loanRequestValidator;

    @Autowired
    private CreateLoanQueryHandler createLoanQueryHandler;

    @Autowired
    private LoanRequestDetailsFactory loanRequestDetailsFactory;


    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> createLoan(@RequestBody LoanRequest loanRequest, HttpServletRequest httpServletRequest) {
        if (!loanRequestValidator.validate(loanRequest)) {
            return new ResponseEntity<>(Message.INVALID_INPUT_FORMAT, HttpStatus.BAD_REQUEST);
        }

        String ip = httpServletRequest.getRemoteAddr();
        loanRequest.setRequestIp(ip);
        LoanRequestDetails loanRequestDetails = loanRequestDetailsFactory.getNewLoanRequestDetails(loanRequest);

        CreateLoanResponse createLoanResponse = createLoanQueryHandler.execute(new CreateLoanQuery(loanRequestDetails));

        String message = createLoanResponse.getMessage();

        if(createLoanResponse.isCreated()) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}

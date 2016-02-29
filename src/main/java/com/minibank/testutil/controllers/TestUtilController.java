package com.minibank.testutil.controllers;

import com.minibank.testutil.repositories.DatabaseCleaner;
import com.minibank.testutil.services.BankConfigurator;
import com.minibank.testutil.services.CustomerConfigurator;
import com.minibank.testutil.services.LoanIdProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/test-util")
public class TestUtilController {

    @Autowired
    private DatabaseCleaner databaseCleaner;

    @Autowired
    private CustomerConfigurator customerConfigurator;

    @Autowired
    private BankConfigurator bankConfigurator;

    @Autowired
    private LoanIdProvider loanIdProvider;


    @RequestMapping(method = RequestMethod.DELETE, value ="/clean-database", produces = "text/plain")
    public ResponseEntity<String> cleanUpDatabase() {
        try {
            databaseCleaner.clear();

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Database cleaned up", HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value ="/bank-parameters", produces = "application/json")
    @Transactional
    public ResponseEntity<String> createTestBankParameters() {
        try {
            bankConfigurator.persistParameters();

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Bank configured", HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.POST, value ="/customers", produces = "application/json")
    @Transactional
    public ResponseEntity<Integer> createTestCustomer() {
        Integer customerId = null;
        try {
             customerId = customerConfigurator.persistCustomer();

        } catch (Exception e) {
            return new ResponseEntity<>(customerId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(customerId, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.GET, value ="/loans", produces = "application/json")
    @Transactional
    public ResponseEntity<Integer> getLasLoanId() {
        Integer loanId = null;
        try {
            loanId = loanIdProvider.getLastLoanId();

        } catch (Exception e) {
            return new ResponseEntity<>(loanId, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(loanId, HttpStatus.CREATED);
    }
}

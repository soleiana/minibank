package com.minibank.rest.controllers;

import com.minibank.communications.CreateLoanExtensionQuery;
import com.minibank.communications.CreateLoanExtensionResponse;
import com.minibank.core.services.CreateLoanExtensionQueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/loans/{id}/extensions")
public class LoanExtensionController {

    @Autowired
    private CreateLoanExtensionQueryHandler createLoanExtensionQueryHandler;


    @RequestMapping(method = RequestMethod.POST, produces = "application/json" )
    public ResponseEntity<String> createLoanExtension(@PathVariable Integer id) {
        CreateLoanExtensionQuery createLoanExtensionQuery = new CreateLoanExtensionQuery(id);
        CreateLoanExtensionResponse createLoanExtensionResponse = createLoanExtensionQueryHandler.execute(createLoanExtensionQuery);
        String message = createLoanExtensionResponse.getMessage();
        if (createLoanExtensionResponse.isCreated()) {
            return new ResponseEntity<>(message, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(message, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}

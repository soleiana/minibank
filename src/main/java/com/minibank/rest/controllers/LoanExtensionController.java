package com.minibank.rest.controllers;

import com.minibank.common.Messages;
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

    private boolean validate(Integer loanId) {
        if (loanId == null) {
            return false;
        } else if (loanId <= 0) {
            return false;
        }
        return  true;
    }

    @RequestMapping(method = RequestMethod.POST, consumes = "application/json")
    public ResponseEntity<String> createLoanExtension(@PathVariable Integer id) {
        if (!validate(id)) {
            return new ResponseEntity<>(Messages.INVALID_INPUT_FORMAT, HttpStatus.BAD_REQUEST);
        }

        CreateLoanExtensionQuery createLoanExtensionQuery = new CreateLoanExtensionQuery(id);

        CreateLoanExtensionResponse createLoanExtensionResponse = createLoanExtensionQueryHandler.execute(createLoanExtensionQuery);
        String message = createLoanExtensionResponse.getMessage();
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }
}

package com.minibank.rest.controllers;

import com.minibank.communications.GetAllLoansQuery;
import com.minibank.communications.GetAllLoansResponse;
import com.minibank.communications.model.AllLoansDetails;
import com.minibank.core.services.GetAllLoansQueryHandler;
import com.minibank.rest.factories.AllLoansRestFactory;
import com.minibank.rest.model.AllLoans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private GetAllLoansQueryHandler getAllLoansQueryHandler;

    @Autowired
    private AllLoansRestFactory allLoansRestFactory;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/loans", produces = "application/json")
    public ResponseEntity<AllLoans> requestAllLoans(@PathVariable Integer id) {

        GetAllLoansQuery getAllLoansQuery = new GetAllLoansQuery(id);

        GetAllLoansResponse getAllLoansResponse = getAllLoansQueryHandler.execute(getAllLoansQuery);
        AllLoansDetails allLoansDetails = getAllLoansResponse.getAllLoansDetails();
        AllLoans allLoans = allLoansRestFactory.getAllLoans(allLoansDetails);

        if (getAllLoansResponse.isEntityFound()) {
            return new ResponseEntity<>(allLoans, HttpStatus.OK);

        } else if (getAllLoansResponse.isErrorResponse()){
            return new ResponseEntity<>(new AllLoans(), HttpStatus.INTERNAL_SERVER_ERROR);

        } else {
            return new ResponseEntity<>(allLoans, HttpStatus.NOT_FOUND);
        }
    }
}

package com.minibank.rest.controllers;

import com.minibank.core.events.loans.AllLoansEvent;
import com.minibank.core.events.loans.RequestAllLoansEvent;
import com.minibank.core.events.loans.domain.AllLoansDetails;
import com.minibank.core.services.LoanService;
import com.minibank.rest.domain.AllLoans;
import com.minibank.rest.factories.AllLoansFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Created by Ann on 16/09/14.
 */

@Controller
@RequestMapping("/rest/customers")
public class LoanQueriesController
{
    @Autowired
    private LoanService loanService;

    @Autowired
    @Qualifier("Rest")
    private AllLoansFactory allLoansFactory;

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/loans")
    @Produces(MediaType.APPLICATION_JSON)
    public ResponseEntity<AllLoans> requestAllLoans(@PathVariable Integer id)
    {
        RequestAllLoansEvent requestAllLoansEvent = new RequestAllLoansEvent(id);
        AllLoansEvent allLoansEvent = loanService.requestAllLoans(requestAllLoansEvent);

        if(allLoansEvent.isEntityFound())
        {
            AllLoansDetails allLoansDetails = allLoansEvent.getAllLoansDetails();
            AllLoans allLoans = allLoansFactory.getNewAllLoans(allLoansDetails);
            return new ResponseEntity<>(allLoans, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

package com.minibank.rest.controllers;

import com.minibank.core.communications.loans.GetAllLoansQuery;
import com.minibank.core.communications.loans.GetAllLoansResponse;
import com.minibank.core.communications.loans.domain.AllLoansDetails;
import com.minibank.core.services.QueryExecutor;
import com.minibank.rest.domain.AllLoans;
import com.minibank.rest.factories.AllLoansRestFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


/**
 * Created by Ann on 16/09/14.
 */

@Controller
@RequestMapping("/rest/customers")
public class LoanQueriesController
{
    @Autowired
    private QueryExecutor queryExecutor;

    @Autowired
    private AllLoansRestFactory allLoansRestFactory;

    @RequestMapping(method = RequestMethod.GET,
                    value = "/{id}/loans",
                    produces = "application/json")
    public ResponseEntity<AllLoans> requestAllLoans(@PathVariable Integer id)
    {
        GetAllLoansQuery getAllLoansQuery = new GetAllLoansQuery(id);

        GetAllLoansResponse getAllLoansResponse = queryExecutor.execute(getAllLoansQuery);

        if(getAllLoansResponse.isEntityFound())
        {
            AllLoansDetails allLoansDetails = getAllLoansResponse.getAllLoansDetails();
            AllLoans allLoans = allLoansRestFactory.getNewAllLoans(allLoansDetails);
            return new ResponseEntity<>(allLoans, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}

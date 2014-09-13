package com.minibank.core.services;

import com.minibank.core.domain.*;
import com.minibank.core.events.loans.*;
import com.minibank.core.services.factories.LoanFactory;
import com.minibank.core.services.factories.LoanRequestFactory;
import com.minibank.core.services.helpers.CreditExpert;
import com.minibank.core.services.helpers.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 07/09/14.
 */
@Component
public class LoanEventHandler implements LoanService
{
    @Autowired
    private CreditExpert creditExpert;
    @Autowired
    private Logger logger;
    @Autowired
    private LoanRequestFactory loanRequestFactory;
    @Autowired
    private LoanFactory loanFactory;

    @Override
    public LoanCreatedEvent createLoan(CreateLoanEvent event)
    {
        //Precondition: customer already logged in and its record exists in database

        Boolean isLoanObtained = false;

        LoanRequestDetails requestDetails = event.getLoanRequestDetails();
        LoanRequest loanRequest = loanRequestFactory.getNewLoanRequest(requestDetails);

        try
        {
            //loanRequest created in DB
            logger.log(loanRequest);

            if (creditExpert.hasRisks(loanRequest))
                loanRequest.setStatus(LoanRequestStatus.REJECTED);
            else
            {
                loanRequest.setStatus(LoanRequestStatus.APPROVED);
                Loan loan = loanFactory.getNewLoan(loanRequest);
                logger.log(loan);
            }
            logger.update(loanRequest);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        //requestDetails = loanRequest.toLoanRequestDetails();
        Integer id = loanRequest.getId();
        //LoanCreatedEvent loanCreatedEvent = new LoanCreatedEvent(id,requestDetails);

        //return  loanCreatedEvent;
        return  null;
    }

    @Override
    public LoanExtensionCreatedEvent createLoanExtension(CreateLoanExtensionEvent event)
    {return  null;}

    @Override
    public AllLoansEvent requestAllLoans(RequestAllLoansEvent event)
    {return  null;}
}

package com.minibank.core.services;

import com.minibank.core.domain.*;
import com.minibank.core.events.loans.*;
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
    LoanFactory loanFactory;

    @Override
    public LoanCreatedEvent createLoan(CreateLoanEvent event)
    {
        //Precondition: customer already logged in and its record exists in database
        LoanRequestDetails requestDetails = event.getLoanRequestDetails();
        LoanRequest loanRequest = LoanRequest.fromLoanRequestDetails(requestDetails);

        try
        {
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
        requestDetails = loanRequest.toLoanRequestDetails();
        Integer id = loanRequest.getId();
        LoanCreatedEvent loanCreatedEvent = new LoanCreatedEvent(id,requestDetails);

        return  loanCreatedEvent;
    }

    @Override
    public LoanExtensionCreatedEvent createLoanExtension(CreateLoanExtensionEvent event)
    {return  null;}

    @Override
    public AllLoansEvent requestAllLoans(RequestAllLoansEvent event)
    {return  null;}
}

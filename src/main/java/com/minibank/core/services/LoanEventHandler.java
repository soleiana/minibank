package com.minibank.core.services;

import com.minibank.core.domain.*;
import com.minibank.core.events.loans.*;
import com.minibank.core.services.common.Message;
import com.minibank.core.services.factories.LoanExtensionFactory;
import com.minibank.core.services.factories.LoanFactory;
import com.minibank.core.services.factories.LoanRequestFactory;
import com.minibank.core.services.helpers.CreditExpert;
import com.minibank.core.services.helpers.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
    @Autowired
    private LoanExtensionFactory loanExtensionFactory;

    @Override
    public LoanCreatedEvent createLoan(CreateLoanEvent event)
    {
        //Precondition: customer already logged in and its record exists in database

        LoanCreatedEvent loanCreated;
        Boolean isLoanObtained = false;

        LoanRequestDetails requestDetails = event.getLoanRequestDetails();

        try
        {
            LoanRequest loanRequest = loanRequestFactory.getNewLoanRequest(requestDetails);
            //loanRequest created in DB
            logger.log(loanRequest);

            if (creditExpert.hasRisks(loanRequest))
                loanRequest.setStatus(LoanRequestStatus.REJECTED);
            else
            {
                loanRequest.setStatus(LoanRequestStatus.APPROVED);
                Loan loan = loanFactory.getNewLoan(loanRequest);
                //loan created in DB
                logger.log(loan);
                loanRequest.setLoan(loan);
                isLoanObtained = true;
            }
            //loanRequest is update with STATUS and LOAN_ID
            logger.update(loanRequest);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        if (isLoanObtained)
        {
            loanCreated = new LoanCreatedEvent(true,null);
        }
        else
            loanCreated = new LoanCreatedEvent(false, Message.LOAN_ERROR_MESSAGE);

        return  loanCreated;

    }

    @Override
    public LoanExtensionCreatedEvent createLoanExtension(CreateLoanExtensionEvent event)
    {
        //Precondition: customer already logged in and its record exists in database
        //Precondition: loan, subject to extension, exists in database

        LoanExtensionCreatedEvent loanExtensionCreated;

        LoanExtensionDetails loanExtensionDetails = event.getLoanExtensionDetails();
        try
        {
            LoanExtension loanExtension = loanExtensionFactory.getNewLoanExtension(loanExtensionDetails);
            //loanExtension created in DB
            logger.log(loanExtension);
            Loan extendedLoan = loanFactory.getExtendedLoan(loanExtension);
            //Extended loan gets persisted
            logger.update(extendedLoan);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
       loanExtensionCreated = new LoanExtensionCreatedEvent(Message.LOAN_EXTENSION_MESSAGE);
       return  loanExtensionCreated;
    }

    @Override
    public AllLoansEvent requestAllLoans(RequestAllLoansEvent event)
    {return  null;}
}

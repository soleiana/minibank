package com.minibank.core.services;

import com.minibank.core.communications.loans.CreateLoanExtensionQuery;
import com.minibank.core.communications.loans.CreateLoanExtensionResponse;
import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import com.minibank.core.services.common.Message;
import com.minibank.core.services.factories.LoanExtensionFactory;
import com.minibank.core.services.factories.LoanFactory;
import com.minibank.core.services.helpers.DBWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


/**
 * Created by Ann on 02/10/14.
 */
@Component
public class CreateLoanExtensionQueryHandler
        implements QueryHandler<CreateLoanExtensionQuery, CreateLoanExtensionResponse>
{
    @Autowired
    private DBWriter dbWriter;
    @Autowired
    private LoanFactory loanFactory;
    @Autowired
    private LoanExtensionFactory loanExtensionFactory;

    @Override
    public CreateLoanExtensionResponse execute(CreateLoanExtensionQuery query)
    {
        //Precondition: customer already logged in and its record exists in database
        //Precondition: loan, subject to extension, exists in database

        Integer loanId = query.getLoanId();
        try
        {
            LoanExtension loanExtension = loanExtensionFactory.getNewLoanExtension(loanId);
            //loanExtension created in DB
            dbWriter.create(loanExtension);
            Loan extendedLoan = loanFactory.getExtendedLoan(loanExtension);
            //Extended loan gets persisted
            dbWriter.update(extendedLoan);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return new CreateLoanExtensionResponse(false, Message.LOAN_EXTENSION_ERROR_MESSAGE);
        }
        return new CreateLoanExtensionResponse(true, Message.LOAN_EXTENSION_OBTAINED_MESSAGE);
    }

    @Override
    public Class getQueryType()
    {
        return CreateLoanExtensionQuery.class;
    }
}

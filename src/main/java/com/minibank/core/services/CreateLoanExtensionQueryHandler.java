package com.minibank.core.services;

import com.minibank.core.communications.loans.CreateLoanExtensionQuery;
import com.minibank.core.communications.loans.CreateLoanExtensionResponse;
import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanExtension;
import com.minibank.core.repositories.LoanExtensionRepository;
import com.minibank.core.repositories.LoanRepository;
import com.minibank.core.services.common.Message;
import com.minibank.core.services.factories.LoanExtensionFactory;
import com.minibank.core.services.factories.LoanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreateLoanExtensionQueryHandler implements QueryHandler<CreateLoanExtensionQuery, CreateLoanExtensionResponse> {

    @Autowired
    private LoanFactory loanFactory;

    @Autowired
    private LoanExtensionFactory loanExtensionFactory;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanExtensionRepository loanExtensionRepository;

    @Override
    public CreateLoanExtensionResponse execute(CreateLoanExtensionQuery query) {
        //Precondition: customer already exists in database
        //Precondition: loan, subject to extension, exists in database

        Integer loanId = query.getLoanId();

        LoanExtension loanExtension = loanExtensionFactory.getNewLoanExtension(loanId);
        loanExtensionRepository.create(loanExtension);
        Loan extendedLoan = loanFactory.getExtendedLoan(loanExtension);
        loanRepository.update(extendedLoan);
        return new CreateLoanExtensionResponse(true, Message.LOAN_EXTENSION_OBTAINED_MESSAGE);
    }

    @Override
    public Class getQueryType() {
        return CreateLoanExtensionQuery.class;
    }
}

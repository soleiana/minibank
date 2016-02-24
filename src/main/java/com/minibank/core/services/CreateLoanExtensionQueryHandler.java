package com.minibank.core.services;

import com.minibank.core.communications.CreateLoanExtensionQuery;
import com.minibank.core.communications.CreateLoanExtensionResponse;
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
public class CreateLoanExtensionQueryHandler {

    @Autowired
    private LoanFactory loanFactory;

    @Autowired
    private LoanExtensionFactory loanExtensionFactory;

    @Autowired
    private LoanRepository loanRepository;

    @Autowired
    private LoanExtensionRepository loanExtensionRepository;


    public CreateLoanExtensionResponse execute(CreateLoanExtensionQuery query) {
        Integer loanId = query.getLoanId();
        Loan loan = loanRepository.getById(loanId);
        LoanExtension loanExtension = loanExtensionFactory.getNewLoanExtension(loan);
        loanExtensionRepository.create(loanExtension);
        Loan extendedLoan = loanFactory.getExtendedLoan(loan, loanExtension);
        loanRepository.update(extendedLoan);
        return new CreateLoanExtensionResponse(true, Message.LOAN_EXTENSION_OBTAINED_MESSAGE);
    }

}

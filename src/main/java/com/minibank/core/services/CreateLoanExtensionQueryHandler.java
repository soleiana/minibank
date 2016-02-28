package com.minibank.core.services;

import com.minibank.common.Messages;
import com.minibank.communications.CreateLoanExtensionQuery;
import com.minibank.communications.CreateLoanExtensionResponse;
import com.minibank.core.factories.LoanExtensionFactory;
import com.minibank.core.factories.LoanFactory;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanExtension;
import com.minibank.core.repositories.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class CreateLoanExtensionQueryHandler {

    @Autowired
    private LoanFactory loanFactory;

    @Autowired
    private LoanExtensionFactory loanExtensionFactory;

    @Autowired
    private LoanRepository loanRepository;


    @Transactional
    public CreateLoanExtensionResponse execute(CreateLoanExtensionQuery query) {
        int loanId = query.getLoanId();
        Loan loan = loanRepository.getById(loanId);
        LoanExtension loanExtension = loanExtensionFactory.getLoanExtension(loan);
        Loan extendedLoan = loanFactory.getExtendedLoan(loan, loanExtension);
        extendedLoan.addLoanExtension(loanExtension);
        loanRepository.update(extendedLoan);
        return new CreateLoanExtensionResponse(true, Messages.LOAN_EXTENSION_OBTAINED_MESSAGE);
    }

}

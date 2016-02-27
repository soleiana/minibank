package com.minibank.core.services;

import com.minibank.common.Messages;
import com.minibank.communications.CreateLoanQuery;
import com.minibank.communications.CreateLoanResponse;
import com.minibank.communications.model.LoanRequestDetails;
import com.minibank.core.factories.LoanFactory;
import com.minibank.core.factories.LoanRequestFactory;
import com.minibank.core.model.Loan;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.LoanRepository;
import com.minibank.core.repositories.LoanRequestRepository;
import com.minibank.core.validators.LoanAmountValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component
public class CreateLoanQueryHandler {

    @Autowired
    private LoanRequestFactory loanRequestFactory;

    @Autowired
    private LoanFactory loanFactory;

    @Autowired
    private LoanExpert loanExpert;

    @Autowired
    private LoanAmountValidator loanAmountValidator;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanRepository loanRepository;


    @Transactional
    public CreateLoanResponse execute(CreateLoanQuery query) {
        LoanRequestDetails requestDetails = query.getLoanRequestDetails();

        LoanRequest loanRequest = loanRequestFactory.getLoanRequest(requestDetails);
        loanRequestRepository.create(loanRequest);

        if (isNegativeCreditDecision(loanRequest)) {
            return new CreateLoanResponse(false, Messages.LOAN_ERROR_MESSAGE);
        } else {
             Loan loan = loanFactory.getNewLoan(loanRequest);
             loanRequest.getCustomer().addLoan(loan);
             loanRepository.create(loan);
             return new CreateLoanResponse(true, Messages.LOAN_OBTAINED_MESSAGE);
        }
    }

    private boolean isNegativeCreditDecision(LoanRequest loanRequest) {
        return !loanAmountValidator.isValid(loanRequest.getAmount()) || loanExpert.riskSurroundsLoan(loanRequest);
    }

}

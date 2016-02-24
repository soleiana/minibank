package com.minibank.core.services;

import com.minibank.core.communications.CreateLoanQuery;
import com.minibank.core.communications.CreateLoanResponse;
import com.minibank.core.communications.domain.LoanRequestDetails;
import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.domain.LoanRequestStatus;
import com.minibank.core.repositories.LoanRepository;
import com.minibank.core.repositories.LoanRequestRepository;
import com.minibank.core.services.common.Message;
import com.minibank.core.services.factories.LoanFactory;
import com.minibank.core.services.factories.LoanRequestFactory;
import com.minibank.core.services.helpers.InputConstraintChecker;
import com.minibank.core.services.helpers.CreditExpert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreateLoanQueryHandler {

    @Autowired
    private LoanRequestFactory loanRequestFactory;

    @Autowired
    private LoanFactory loanFactory;

    @Autowired
    private CreditExpert creditExpert;

    @Autowired
    private InputConstraintChecker inputConstraintChecker;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private LoanRepository loanRepository;


    public CreateLoanResponse execute(CreateLoanQuery query) {
        LoanRequestDetails requestDetails = query.getLoanRequestDetails();

        LoanRequest loanRequest = loanRequestFactory.getNewLoanRequest(requestDetails);
        loanRequestRepository.create(loanRequest);

        if (isNegativeCreditDecision(loanRequest)) {
            loanRequest.setStatus(LoanRequestStatus.REJECTED);
            return new CreateLoanResponse(false, Message.LOAN_ERROR_MESSAGE);
        } else {
             loanRequest.setStatus(LoanRequestStatus.APPROVED);
             Loan loan = loanFactory.getNewLoan(loanRequest);
             loanRepository.create(loan);
             return new CreateLoanResponse(true, Message.LOAN_OBTAINED_MESSAGE);
        }
    }

    private boolean isNegativeCreditDecision(LoanRequest loanRequest) {
        return !inputConstraintChecker.isEqualOrLessThanMaxLoanAmount(loanRequest.getAmount()) || creditExpert.hasRisks(loanRequest);
    }

}

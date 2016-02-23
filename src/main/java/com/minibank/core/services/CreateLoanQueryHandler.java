package com.minibank.core.services;

import com.minibank.core.communications.loans.CreateLoanQuery;
import com.minibank.core.communications.loans.CreateLoanResponse;
import com.minibank.core.communications.loans.domain.LoanRequestDetails;
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
public class CreateLoanQueryHandler implements QueryHandler<CreateLoanQuery, CreateLoanResponse> {

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

    @Override
    public CreateLoanResponse execute(CreateLoanQuery query) {
        //Precondition: customer already exists in database

        Boolean isLoanObtained = false;
        LoanRequestDetails requestDetails = query.getLoanRequestDetails();

        LoanRequest loanRequest = loanRequestFactory.getNewLoanRequest(requestDetails);
        loanRequestRepository.create(loanRequest);

        if((!inputConstraintChecker.checkAmountConstraint(loanRequest))|| creditExpert.hasRisks(loanRequest)) {
            loanRequest.setStatus(LoanRequestStatus.REJECTED);
        }
        else {
             loanRequest.setStatus(LoanRequestStatus.APPROVED);
             Loan loan = loanFactory.getNewLoan(loanRequest);
             loanRepository.create(loan);
             isLoanObtained = true;
        }
        if (isLoanObtained) {
            return new CreateLoanResponse(true, Message.LOAN_OBTAINED_MESSAGE);
        }
        else {
            return new CreateLoanResponse(false, Message.LOAN_ERROR_MESSAGE);
        }
    }

    @Override
    public Class getQueryType() {
        return CreateLoanQuery.class;
    }
}

package com.minibank.core.services;

import com.minibank.core.model.LoanRequest;
import com.minibank.core.rules.LoanRequestSubmissionTimeRule;
import com.minibank.core.rules.MaxLoanAmountRule;
import com.minibank.core.rules.MaxLoanAttemptsPerIpRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LoanExpert {

    @Autowired
    private MaxLoanAmountRule maxLoanAmountRule;

    @Autowired
    private MaxLoanAttemptsPerIpRule maxLoanAttemptsPerIpRule;

    @Autowired
    private LoanRequestSubmissionTimeRule loanRequestSubmissionTimeRule;


    public boolean riskSurroundsLoan(LoanRequest loanRequest) {
        return maxLoanAttemptsPerIpRule.holdsTrue(loanRequest)
                || maxLoanAmountRule.holdsTrue(loanRequest)
                && loanRequestSubmissionTimeRule.holdsTrue(loanRequest);
    }
}

package com.minibank.core.services.helpers;

import com.minibank.core.domain.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreditExpert {

    @Autowired
    private RiskConstraintChecker checker;

    public boolean hasRisks(LoanRequest loanRequest) {
        return checker.isMaxRequestsPerIPExceeded(loanRequest) || checker.isMaxAmount(loanRequest) && checker.isRiskTime(loanRequest);
    }
}

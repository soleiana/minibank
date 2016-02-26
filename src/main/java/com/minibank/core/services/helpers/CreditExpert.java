package com.minibank.core.services.helpers;

import com.minibank.core.model.LoanRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class CreditExpert {

    @Autowired
    private RiskConstraintChecker checker;

    public boolean hasRisks(LoanRequest loanRequest) {
        return checker.isMaxRequestsPerIpExceeded(loanRequest) || checker.isMaxAmount(loanRequest) && checker.isRiskTime(loanRequest);
    }
}

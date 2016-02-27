package com.minibank.core.rules;

import com.minibank.core.common.AppParametersProvider;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.LoanRequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class MaxLoanAttemptsPerIpRule {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private AppParametersProvider parametersProvider;


    public boolean holdsTrue(LoanRequest loanRequest) {
        BankParameters bankParams = parametersProvider.getBankParameters();
        Byte maxLoanAttempts = bankParams.getMaxLoanAttempts();
        String requestIp = loanRequest.getRequestIp();
        List<LoanRequest> loanRequestsPerIp = loanRequestRepository.getByRequestIp(requestIp);
        return countNumberOfLoanRequestsToday(loanRequestsPerIp) > maxLoanAttempts;
    }

    private long countNumberOfLoanRequestsToday(List<LoanRequest> loanRequests) {
        LocalDate now = LocalDate.now();
        return loanRequests.stream()
                .filter(loanRequest -> loanRequest.getSubmissionDate().isEqual(now))
                .count();
    }

}

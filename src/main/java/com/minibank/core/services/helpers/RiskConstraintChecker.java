package com.minibank.core.services.helpers;

import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.BankParametersRepository;
import com.minibank.core.repositories.LoanRequestRepository;
import com.minibank.core.services.common.DateTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;


@Component
public class RiskConstraintChecker {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    @Autowired
    private BankParametersRepository bankParametersRepository;

    public boolean isMaxRequestsPerIPExceeded(LoanRequest loanRequest) {
        BankParameters bankParams = getBankParameters();
        Byte maxLoanAttempts = bankParams.getMaxLoanAttempts();

        String requestIp = loanRequest.getRequestIp();
        List<LoanRequest> loanRequests = loanRequestRepository.getByRequestIp(requestIp);

        Date now = new Date();
        java.sql.Date sqlNow = DateTimeUtility.getSqlDate(now);

        int numberOfRequests = 0;

        for(LoanRequest req: loanRequests)
            if (req.getSubmissionDate().equals(sqlNow))
                numberOfRequests++;

        return numberOfRequests > maxLoanAttempts;

    }

    public boolean isRiskTime(LoanRequest loanRequest) {
        BankParameters bankParams = getBankParameters();
        Time riskTimeStart = bankParams.getRiskTimeStart();
        Time riskTimeEnd = bankParams.getRiskTimeEnd();

        Time submissionTime = loanRequest.getSubmissionTime();

        if (riskTimeStart.compareTo(riskTimeEnd) == 1) {
            //check two time intervals: [riskTimeStart, 24:00:00]
            //and [00:00:00, riskTimeEnd]
            return isBetween(riskTimeStart, DateTimeUtility.MAX_TIME, submissionTime) || isBetween(DateTimeUtility.MIN_TIME, riskTimeEnd, submissionTime);
        } else {
            //check one time interval: [riskTimeStart, riskTimeEnd]
            return isBetween(riskTimeStart, riskTimeEnd, submissionTime);
        }

    }

    public boolean isMaxAmount(LoanRequest loanRequest) {
        BankParameters bankParams = getBankParameters();
        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        BigDecimal loanRequestAmount = loanRequest.getAmount();
        return loanRequestAmount.compareTo(maxLoanAmount) == 0;
    }

    private boolean isBetween(Time start, Time end, Time timeToCheck) {
        return timeToCheck.compareTo(start) == 1 && end.compareTo(timeToCheck) == 1;
    }

    private BankParameters getBankParameters() {
        return bankParametersRepository.getCurrentBankParameters();
    }
}

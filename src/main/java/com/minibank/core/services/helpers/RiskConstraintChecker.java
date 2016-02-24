package com.minibank.core.services.helpers;

import com.minibank.core.domain.BankParameters;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repositories.LoanRequestRepository;
import com.minibank.core.services.common.DateTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;


@Component
public class RiskConstraintChecker extends ConstraintChecker {

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    public boolean checkMaxRequestsPerIP(LoanRequest loanRequest) {
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

        return numberOfRequests <= maxLoanAttempts;

    }

    public boolean checkTimeConstraint(LoanRequest loanRequest) {
        BankParameters bankParams = getBankParameters();
        Time riskTimeStart = bankParams.getRiskTimeStart();
        Time riskTimeEnd = bankParams.getRiskTimeEnd();

        Time submissionTime = loanRequest.getSubmissionTime();

        if (riskTimeStart.compareTo(riskTimeEnd) == 1)
            //check two time intervals: [riskTimeStart, 24:00:00]
            //and [00:00:00, riskTimeEnd]
            if ( isBetween(riskTimeStart, DateTimeUtility.MAX_TIME, submissionTime) ||
                    isBetween(DateTimeUtility.MIN_TIME, riskTimeEnd, submissionTime)
                    )
                return false;
            else
                return true;
        else
            //check one time interval: [riskTimeStart, riskTimeEnd]
            if (isBetween(riskTimeStart, riskTimeEnd, submissionTime))
                return false;
            else
                return  true;

    }

    public boolean isMaxAmount(LoanRequest loanRequest) {
        BankParameters bankParams = getBankParameters();
        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        BigDecimal reqAmount = loanRequest.getAmount();

        if(reqAmount.compareTo(maxLoanAmount) == 0)
            return true;
        else
            return false;
    }

    private boolean isBetween(Time start, Time end, Time timeToCheck) {
        return timeToCheck.compareTo(start) == 1 && end.compareTo(timeToCheck) == 1;

    }
}

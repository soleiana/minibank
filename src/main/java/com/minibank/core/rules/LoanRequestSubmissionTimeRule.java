package com.minibank.core.rules;

import com.minibank.common.DateTimeParameters;
import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import org.springframework.stereotype.Component;

import java.time.LocalTime;

@Component
public class LoanRequestSubmissionTimeRule extends Rule {

    @Override
    public boolean holdsTrue(LoanRequest loanRequest) {
        BankParameters bankParams = getBankParameters();
        LocalTime riskTimeStart = bankParams.getRiskTimeStart();
        LocalTime riskTimeEnd = bankParams.getRiskTimeEnd();

        LocalTime submissionTime = loanRequest.getSubmissionTime();

        if (riskTimeStart.isAfter(riskTimeEnd)) {
            return isBetween(submissionTime, riskTimeStart, DateTimeParameters.MAX_TIME)
                    || submissionTime.equals(DateTimeParameters.MAX_TIME)
                    || isBetween(submissionTime, DateTimeParameters.MIN_TIME, riskTimeEnd);

        } else {
            return isBetween(submissionTime, riskTimeStart, riskTimeEnd);
        }
    }

    private boolean isBetween(LocalTime timeToCheck, LocalTime start, LocalTime end) {
        return timeToCheck.isAfter(start) && timeToCheck.isBefore(end);
    }
}

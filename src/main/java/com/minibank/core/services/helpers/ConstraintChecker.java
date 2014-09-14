package com.minibank.core.services.helpers;

import com.minibank.core.domain.BankParams;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.domain.RequestIP;
import com.minibank.core.repository.BankParamsRepository;
import com.minibank.core.repository.DBException;
import com.minibank.core.repository.LoanRequestRepository;
import com.minibank.core.services.common.DateTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Time;
import java.util.Date;
import java.util.List;

/**
 * Created by Ann on 13/09/14.
 */
@Component
public class ConstraintChecker
{
    @Autowired
    private BankParamsRepository bankParamsRepository;

    @Autowired
    private LoanRequestRepository loanRequestRepository;

    private BankParams bankParams;

    private void initBankParams() throws DBException
    {
        bankParams = bankParamsRepository.getLast();
    }

    public boolean checkMaxRequestsPerIP(LoanRequest loanRequest) throws DBException
    {
        initBankParams();
        Byte maxLoanAttempts = bankParams.getMaxLoanAttempts();

        RequestIP requestIP = loanRequest.getRequestIP();

        List<LoanRequest> loanRequests = loanRequestRepository.getByRequestIP(requestIP);

        Date now = new Date();
        java.sql.Date sqlNow = DateTimeUtility.getSqlDate(now);

        int requestNum = 0;

        for(LoanRequest req: loanRequests)
            if (req.getSubmissionDate().equals(sqlNow))
                requestNum++;

        if (requestNum < maxLoanAttempts)
            return true;
        else
            return false;
    }

    public boolean checkTimeConstraint(LoanRequest loanRequest) throws DBException
    {
        initBankParams();
        Time riskTimeStart = bankParams.getRiskTimeStart();
        Time riskTimeEnd = bankParams.getRiskTimeEnd();

        Time submissionTime = loanRequest.getSubmissionTime();

        if (riskTimeStart.compareTo(riskTimeEnd)==1)
            //check two time intervals: [riskTimeStart, 23:59:59]
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

    private boolean isBetween(Time start, Time end, Time timeToCheck)
    {
        if ((timeToCheck.compareTo(start)==1)&&(end.compareTo(timeToCheck)==1))
            return true;

        else
            return false;
    }

    public boolean checkAmountConstraint(LoanRequest loanRequest) throws DBException
    {
        initBankParams();

        BigDecimal maxLoanAmount = bankParams.getMaxLoanAmount();
        BigDecimal reqAmount = loanRequest.getAmount();

        if(reqAmount.compareTo(maxLoanAmount) >= 0)
            return false;
        else
            return true;
    }
}

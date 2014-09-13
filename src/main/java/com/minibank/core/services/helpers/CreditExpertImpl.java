package com.minibank.core.services.helpers;

import com.minibank.core.domain.BankParams;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.domain.RequestIP;
import com.minibank.core.repository.BankParamsRepository;
import com.minibank.core.repository.DBException;
import com.minibank.core.services.common.DateTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.sql.Time;

/**
 * Created by Ann on 12/09/14.
 */
@Component
public class CreditExpertImpl implements CreditExpert
{
    @Autowired
    private BankParamsRepository bankParamsRepository;

    private BankParams bankParams;

    @Override
    public boolean hasRisks(LoanRequest loanRequest) throws DBException
    {
        if (!checkMaxRequestsPerIP(loanRequest)
                ||
                  (
                    !checkAmountConstraint(loanRequest)

                    &&

                     !checkTimeConstraint(loanRequest)
                  )
           )
           return false;
        else
           return  true;
    }

    private void initBankParams() throws DBException
    {
        bankParams = bankParamsRepository.getLast();
    }

    private boolean checkMaxRequestsPerIP(LoanRequest loanRequest) throws DBException
    {
        initBankParams();
        Byte maxLoanAttempts = bankParams.getMaxLoanAttempts();

        RequestIP requestIP = loanRequest.getRequestIP();
        List<LoanRequest> loanRequests = requestIP.getLoanRequests();

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

    private boolean checkTimeConstraint(LoanRequest loanRequest) throws DBException
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

    private boolean checkAmountConstraint(LoanRequest loanRequest) throws DBException
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

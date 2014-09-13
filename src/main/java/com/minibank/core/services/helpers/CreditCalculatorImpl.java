package com.minibank.core.services.helpers;

import com.minibank.core.domain.BankParams;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.repository.BankParamsRepository;
import com.minibank.core.services.common.Number;
import com.minibank.core.repository.DBException;
import com.minibank.core.services.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;


/**
 * Created by Ann on 12/09/14.
 */
@Component
public class CreditCalculatorImpl implements CreditCalculator
{
    @Autowired
    private BankParamsRepository bankParamsRepository;

    @Override
    public Date getLoanEndDate(LoanRequest loanRequest)
    {
        Date startDate = loanRequest.getSubmissionDate();
        Integer term = loanRequest.getTerm();

        SimpleDateFormat sdf = new SimpleDateFormat(DateTimeUtility.DATE_FORMAT);
        Calendar c = Calendar.getInstance();
        c.setTime(startDate);
        c.add(Calendar.DATE, term);
        String output = sdf.format(c.getTime());
        Date endDate = Date.valueOf(output);
        return  endDate;
    }

    @Override
    public BigDecimal getInterest(LoanRequest loanRequest) throws DBException
    {
        BankParams bankParams = bankParamsRepository.getLast();
        BigDecimal baseInterestRate = bankParams.getBaseInterestRate();
        BigDecimal amount = loanRequest.getAmount();
        BigDecimal term = new BigDecimal(loanRequest.getTerm());

        BigDecimal interest = amount.multiply(baseInterestRate).multiply(term).divide(Number.DENOMINATOR);

        return interest;
    }
}

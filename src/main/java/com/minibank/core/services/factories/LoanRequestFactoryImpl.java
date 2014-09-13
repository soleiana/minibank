package com.minibank.core.services.factories;

import com.minibank.core.domain.Customer;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.domain.LoanRequestStatus;
import com.minibank.core.domain.RequestIP;
import com.minibank.core.events.loans.LoanRequestDetails;
import com.minibank.core.services.common.Format;
import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.Date;
import java.text.SimpleDateFormat;


/**
 * Created by Ann on 13/09/14.
 */
@Component
public class LoanRequestFactoryImpl implements LoanRequestFactory
{


    @Override
    public LoanRequest getNewLoanRequest(LoanRequestDetails loanRequestDetails)
    {
        LoanRequest loanRequest = new LoanRequest();

        RequestIP requestIP = new RequestIP(loanRequestDetails.getRequestIP());
        Customer customer = new Customer(loanRequestDetails.getCustomerId());
        loanRequest.setRequestIP(requestIP);
        loanRequest.setCustomer(customer);
        loanRequest.setAmount(loanRequestDetails.getAmount());
        loanRequest.setTerm(loanRequestDetails.getTerm());
        loanRequest.setStatus(LoanRequestStatus.NEW);

        Date dNow = new Date();

        java.sql.Date submissionDate = getSubmissionDate(dNow);
        java.sql.Time submissionTime = getSubmissionTime(dNow);

        loanRequest.setSubmissionDate(submissionDate);
        loanRequest.setSubmissionTime(submissionTime);

        return loanRequest;
    }

    private java.sql.Date getSubmissionDate(Date dNow)
    {

        SimpleDateFormat ft = new SimpleDateFormat(Format.DATE_FORMAT);
        String output = ft.format(dNow);
        return java.sql.Date.valueOf(output);
    }

    private java.sql.Time getSubmissionTime(Date dNow)
    {

        SimpleDateFormat ft = new SimpleDateFormat(Format.TIME_FORMAT);
        String output = ft.format(dNow);
        return Time.valueOf(output);
    }
}

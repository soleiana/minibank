package com.minibank.core.services.factories;

import com.minibank.core.domain.Customer;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.domain.LoanRequestStatus;
import com.minibank.core.domain.RequestIP;
import com.minibank.core.communications.loans.domain.LoanRequestDetails;
import com.minibank.core.repositories.CustomerRepository;
import com.minibank.core.repositories.DBException;
import com.minibank.core.services.common.DateTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Created by Ann on 13/09/14.
 */
@Component
public class LoanRequestFactory
{
    @Autowired
    CustomerRepository customerRepository;

    public LoanRequest getNewLoanRequest(LoanRequestDetails loanRequestDetails) throws DBException
    {
        //We assume that customer already exists in DB
        Integer id = loanRequestDetails.getCustomerId();
        Customer customer = customerRepository.getById(id);

        LoanRequest loanRequest = new LoanRequest();
        RequestIP requestIP = new RequestIP(loanRequestDetails.getRequestIP());

        loanRequest.setRequestIP(requestIP);
        loanRequest.setCustomer(customer);
        loanRequest.setAmount(loanRequestDetails.getAmount());
        loanRequest.setTerm(loanRequestDetails.getTerm());
        loanRequest.setStatus(LoanRequestStatus.NEW);

        Date dNow = new Date();

        java.sql.Date submissionDate = DateTimeUtility.getSqlDate(dNow);
        java.sql.Time submissionTime = DateTimeUtility.getSqlTime(dNow);

        loanRequest.setSubmissionDate(submissionDate);
        loanRequest.setSubmissionTime(submissionTime);

        return loanRequest;
    }
}

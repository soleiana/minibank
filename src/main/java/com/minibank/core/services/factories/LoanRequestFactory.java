package com.minibank.core.services.factories;

import com.minibank.core.domain.Customer;
import com.minibank.core.domain.LoanRequest;
import com.minibank.core.communications.domain.LoanRequestDetails;
import com.minibank.core.repositories.CustomerRepository;
import com.minibank.core.services.common.DateTimeUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;


@Component
public class LoanRequestFactory {

    @Autowired
    CustomerRepository customerRepository;

    public LoanRequest getLoanRequest(LoanRequestDetails loanRequestDetails) {

        Integer id = loanRequestDetails.getCustomerId();
        Customer customer = customerRepository.getById(id);

        LoanRequest loanRequest = new LoanRequest();

        loanRequest.setRequestIp(loanRequestDetails.getRequestIp());
        loanRequest.setCustomer(customer);
        loanRequest.setAmount(loanRequestDetails.getAmount());
        loanRequest.setTerm(loanRequestDetails.getTerm());
        Date dNow = new Date();

        java.sql.Date submissionDate = DateTimeUtility.getSqlDate(dNow);
        java.sql.Time submissionTime = DateTimeUtility.getSqlTime(dNow);

        loanRequest.setSubmissionDate(submissionDate);
        loanRequest.setSubmissionTime(submissionTime);
        return loanRequest;
    }
}

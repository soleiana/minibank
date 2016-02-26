package com.minibank.core.services.factories;

import com.minibank.communications.model.LoanRequestDetails;
import com.minibank.core.model.Customer;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;
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

        LocalDate submissionDate = LocalDate.now();
        LocalTime submissionTime = LocalTime.now();

        loanRequest.setSubmissionDate(submissionDate);
        loanRequest.setSubmissionTime(submissionTime);
        return loanRequest;
    }
}

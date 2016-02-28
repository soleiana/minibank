package com.minibank.core.factories;

import com.minibank.communications.model.LoanRequestDetails;
import com.minibank.core.model.Customer;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


@Component
public class LoanRequestCoreFactory {

    @Autowired
    CustomerRepository customerRepository;

    public LoanRequest getLoanRequest(LoanRequestDetails loanRequestDetails) {
        LoanRequest loanRequest = new LoanRequest();

        loanRequest.setRequestIp(loanRequestDetails.getRequestIp());
        loanRequest.setAmount(loanRequestDetails.getAmount());
        loanRequest.setTerm(loanRequestDetails.getTerm());

        LocalDateTime now = LocalDateTime.now();
        LocalDate submissionDate = now.toLocalDate();
        LocalTime submissionTime = now.toLocalTime();

        loanRequest.setSubmissionDate(submissionDate);
        loanRequest.setSubmissionTime(submissionTime);

        Customer customer = getCustomer(loanRequestDetails.getCustomerId());
        loanRequest.setCustomer(customer);

        return loanRequest;
    }

    private Customer getCustomer(int customerId) {
        return customerRepository.getById(customerId);
    }
}

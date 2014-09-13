package com.minibank.core.services.helpers;

import com.minibank.core.domain.*;
import com.minibank.core.repository.*;
import com.minibank.core.services.helpers.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 12/09/14.
 */
@Component
public class LoggerImpl implements Logger
{
    @Autowired
    private LoanRequestRepository loanRequestRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RequestIPRepository requestIPRepository;
    @Autowired
    private LoanRepository loanRepository;

    @Override
    public void log(LoanRequest loanRequest) throws DBException
    {
        RequestIP requestIP = loanRequest.getRequestIP();
        Customer customer = loanRequest.getCustomer();

        customerRepository.getById(customer.getId());
        log(requestIP);
        loanRequestRepository.create(loanRequest);
    }

    @Override
    public void update(LoanRequest loanRequest)throws DBException
    {
        loanRequestRepository.update(loanRequest);
    }


    private void log(RequestIP requestIP) throws DBException
    {
        RequestIP req = requestIPRepository.getByIP(requestIP.getIP());
        if (req == null)
            requestIPRepository.create(requestIP);

    }

    @Override
    public void log(Loan loan) throws  DBException
    {
        loanRepository.create(loan);
    }

    @Override
    public void log(LoanExtension loanExtension) throws DBException
    {}
}

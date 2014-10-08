package com.minibank.core.services.helpers;

import com.minibank.core.domain.*;
import com.minibank.core.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 12/09/14.
 */
@Component
public class DBWriter
{
    @Autowired
    private LoanRequestRepository loanRequestRepository;
    @Autowired
    private RequestIPRepository requestIPRepository;
    @Autowired
    private LoanRepository loanRepository;
    @Autowired
    private LoanExtensionRepository loanExtensionRepository;

    private void log(RequestIP requestIP)
    {
        RequestIP req = requestIPRepository.getByIP(requestIP.getIP());
        if (req == null)
            requestIPRepository.create(requestIP);
    }

    public void create(LoanRequest loanRequest)
    {
        RequestIP requestIP = loanRequest.getRequestIP();
        log(requestIP);
        loanRequestRepository.create(loanRequest);
    }

    public void update(LoanRequest loanRequest)
    {
        loanRequestRepository.update(loanRequest);
    }

    public void update(Loan extendedLoan)
    {
        loanRepository.update(extendedLoan);
    }

    public void create(Loan loan)
    {
        loanRepository.create(loan);
    }

    public void create(LoanExtension loanExtension)
    {
        loanExtensionRepository.create(loanExtension);
    }
}

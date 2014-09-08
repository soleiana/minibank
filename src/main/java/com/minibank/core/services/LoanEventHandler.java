package com.minibank.core.services;

import com.minibank.config.CoreConfig;
import com.minibank.core.domain.BankParams;
import com.minibank.core.repository.BankParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 07/09/14.
 */
@Component
public class LoanEventHandler implements LoanService
{
    @Autowired
    private BankParamsRepository repository;


    @Override
    public BankParamsRepository getRepository()
    {
        return  repository;
    }

  /*  public LoanEventHandler(final BankParamsRepository repository) {
        this.repository = repository;
    }*/
}

package com.minibank.core.services.helpers;

import com.minibank.core.domain.BankParams;
import com.minibank.core.repositories.BankParamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public abstract class ConstraintChecker {

    @Autowired
    private BankParamsRepository bankParamsRepository;

    protected BankParams getBankParams() {
        return bankParamsRepository.getLast();
    }
}

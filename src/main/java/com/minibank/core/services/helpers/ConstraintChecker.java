package com.minibank.core.services.helpers;

import com.minibank.core.model.BankParameters;
import com.minibank.core.repositories.BankParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public abstract class ConstraintChecker {

    @Autowired
    private BankParametersRepository bankParametersRepository;

    protected BankParameters getBankParameters() {
        return bankParametersRepository.getCurrentBankParameters();
    }
}

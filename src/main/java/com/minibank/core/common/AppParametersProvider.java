package com.minibank.core.common;

import com.minibank.core.model.BankParameters;
import com.minibank.core.repositories.BankParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AppParametersProvider {

    @Autowired
    private BankParametersRepository bankParametersRepository;

    public BankParameters getBankParameters() {
        return bankParametersRepository.getCurrentBankParameters();
    }
}

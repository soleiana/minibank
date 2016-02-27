package com.minibank.core.rules;

import com.minibank.core.model.BankParameters;
import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.BankParametersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public abstract class Rule {

    @Autowired
    private BankParametersRepository bankParametersRepository;

    public abstract boolean holdsTrue(LoanRequest loanRequest);

    protected BankParameters getBankParameters() {
        return bankParametersRepository.getCurrentBankParameters();
    }

}

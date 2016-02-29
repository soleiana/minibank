package com.minibank.core.repositories;

import com.minibank.core.model.BankParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class BankParametersRepository extends SessionProvider {

    @Autowired
    private RepositoryTemplateMethod<BankParameters> repositoryTemplateMethod;

    public BankParameters getCurrentBankParameters() {
        return repositoryTemplateMethod.getLast(BankParameters.class);
    }
}

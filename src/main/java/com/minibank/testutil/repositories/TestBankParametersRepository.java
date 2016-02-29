package com.minibank.testutil.repositories;

import com.minibank.core.model.BankParameters;
import com.minibank.core.repositories.SessionProvider;
import org.springframework.stereotype.Component;

@Component
public class TestBankParametersRepository extends SessionProvider {

    public void create(BankParameters bankParameters) {
        getCurrentSession().saveOrUpdate(bankParameters);
    }
}

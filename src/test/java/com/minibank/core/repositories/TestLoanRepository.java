package com.minibank.core.repositories;

import com.minibank.core.model.Loan;
import com.minibank.core.repositories.helpers.TestRepositoryTemplateMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestLoanRepository {

    @Autowired
    private TestRepositoryTemplateMethod<Loan> testRepositoryTemplateMethod;

    public Loan getLast() {
        return testRepositoryTemplateMethod.getLast(Loan.class);
    }
}

package com.minibank.core.repositories;

import com.minibank.core.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestLoanRepository {

    @Autowired
    private RepositoryTemplateMethod<Loan> repositoryTemplateMethod;

    public Loan getLast() {
        return repositoryTemplateMethod.getLast(Loan.class);
    }
}

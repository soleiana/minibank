package com.minibank.testutil.repositories;

import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.RepositoryTemplateMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestLoanRequestRepository {

    @Autowired
    private RepositoryTemplateMethod<LoanRequest> repositoryTemplateMethod;

    public LoanRequest getLast() {
        return repositoryTemplateMethod.getLast(LoanRequest.class);
    }

}

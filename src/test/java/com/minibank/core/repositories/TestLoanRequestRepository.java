package com.minibank.core.repositories;

import com.minibank.core.model.LoanRequest;
import com.minibank.core.repositories.helpers.TestRepositoryTemplateMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TestLoanRequestRepository {

    @Autowired
    private TestRepositoryTemplateMethod<LoanRequest> testRepositoryTemplateMethod;

    public LoanRequest getLast() {
        return testRepositoryTemplateMethod.getLast(LoanRequest.class);
    }

}

package com.minibank.core.repositories;

import com.minibank.core.model.LoanExtension;
import com.minibank.core.repositories.helpers.TestRepositoryTemplateMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TestLoanExtensionRepository {

    @Autowired
    private TestRepositoryTemplateMethod<LoanExtension> testRepositoryTemplateMethod;

    public LoanExtension getLast() {
       return testRepositoryTemplateMethod.getLast(LoanExtension.class);
    }
}

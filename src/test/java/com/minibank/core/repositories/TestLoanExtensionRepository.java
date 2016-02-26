package com.minibank.core.repositories;

import com.minibank.core.model.LoanExtension;
import com.minibank.core.repositories.helpers.RepositoryTemplateMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class TestLoanExtensionRepository {

    @Autowired
    private RepositoryTemplateMethod<LoanExtension> repositoryTemplateMethod;

    public LoanExtension getLast() {
       return repositoryTemplateMethod.getLast(LoanExtension.class);
    }
}

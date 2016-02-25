package com.minibank.core.repositories;

import com.minibank.core.model.LoanExtension;
import org.springframework.stereotype.Component;


@Component
public class LoanExtensionRepository extends SessionProvider {

    public void create(LoanExtension loanExtension) {
        getCurrentSession().saveOrUpdate(loanExtension);
    }
}

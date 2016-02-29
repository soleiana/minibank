package com.minibank.core.repositories;


import com.minibank.core.model.Loan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class LoanRepository extends SessionProvider {

    @Autowired
    private RepositoryTemplateMethod<Loan> repositoryTemplateMethod;

    public void create(Loan loan) {
        getCurrentSession().saveOrUpdate(loan);
    }

    public void update(Loan loan) {
        getCurrentSession().saveOrUpdate(loan);
    }

    public Loan getById(Integer id) {
        return getCurrentSession().get(Loan.class, id);
    }

    public Loan getLast() {
        return repositoryTemplateMethod.getLast(Loan.class);
    }
}

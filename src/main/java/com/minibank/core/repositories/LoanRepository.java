package com.minibank.core.repositories;


import com.minibank.core.model.Loan;
import org.springframework.stereotype.Component;


@Component
public class LoanRepository extends SessionProvider {

    public void create(Loan loan) {
        getCurrentSession().saveOrUpdate(loan);
    }

    public void update(Loan loan) {
        getCurrentSession().saveOrUpdate(loan);
    }

    public Loan getById(Integer id) {
        return getCurrentSession().get(Loan.class, id);
    }
}

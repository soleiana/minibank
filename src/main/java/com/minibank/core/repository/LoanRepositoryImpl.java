package com.minibank.core.repository;

import com.minibank.core.domain.Loan;
import com.minibank.core.domain.LoanRequest;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ann on 10/09/14.
 */
@Component
public class LoanRepositoryImpl extends SessionProvider
    implements LoanRepository
{
    @Override
    public void create(Loan loan) throws DBException
    {
        getCurrentSession().saveOrUpdate(loan);
    }

    @Override
    public void update(Loan loan) throws DBException
    {
        getCurrentSession().saveOrUpdate(loan);
    }

    @Override
    public Loan getById(Integer id) throws DBException
    {
        return (Loan) getCurrentSession().get(Loan.class, id);
    }
}

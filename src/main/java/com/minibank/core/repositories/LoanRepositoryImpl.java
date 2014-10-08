package com.minibank.core.repositories;

import com.minibank.core.domain.Customer;
import com.minibank.core.domain.Loan;
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
    public void create(Loan loan)
    {
        getCurrentSession().saveOrUpdate(loan);
    }

    @Override
    public void update(Loan loan)
    {
        getCurrentSession().saveOrUpdate(loan);
    }

    @Override
    public Loan getById(Integer id)
    {
        return (Loan) getCurrentSession().get(Loan.class, id);
    }

    @Override
    public List<Loan> getByCustomer(Customer customer)
    {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Loan where customer = :customer");
        query.setParameter("customer", customer);
        return query.list();
    }

    @Override
    public Loan getLast()
    {
        Session session = getCurrentSession();
        Query query = session.createQuery("from Loan order by id DESC");
        query.setMaxResults(1);
        return  (Loan) query.uniqueResult();
    }
}

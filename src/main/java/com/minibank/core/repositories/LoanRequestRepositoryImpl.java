package com.minibank.core.repositories;

import com.minibank.core.domain.LoanRequest;
import com.minibank.core.domain.RequestIP;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Ann on 10/09/14.
 */
@Component
public class LoanRequestRepositoryImpl extends SessionProvider
    implements LoanRequestRepository
{
    @Override
    public void create(LoanRequest loanRequest)
    {
        getCurrentSession().saveOrUpdate(loanRequest);
    }

    @Override
    public void update(LoanRequest loanRequest)
    {
        getCurrentSession().saveOrUpdate(loanRequest);
    }

    @Override
    public LoanRequest getById(Integer id)
    {
        Session session = getCurrentSession();
        return (LoanRequest) session.get(LoanRequest.class, id);
    }

    @Override
    public  List<LoanRequest> getByRequestIP(RequestIP requestIP)
    {
        Session session = getCurrentSession();
        Query query = session.createQuery("from LoanRequest where requestIP = :requestIP");
        query.setParameter("requestIP", requestIP);
        return query.list();
    }

    @Override
    public LoanRequest getLast()
    {
        Session session = getCurrentSession();
        Query query = session.createQuery("from LoanRequest order by id DESC");
        query.setMaxResults(1);
        return  (LoanRequest) query.uniqueResult();
    }
}

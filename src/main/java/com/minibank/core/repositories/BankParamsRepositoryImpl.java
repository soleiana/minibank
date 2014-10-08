package com.minibank.core.repositories;

import com.minibank.core.domain.BankParams;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

/**
 * Created by Ann on 07/09/14.
 */
@Component
public class BankParamsRepositoryImpl extends SessionProvider
        implements BankParamsRepository
{
    @Override
    public void create(BankParams bankParams)
    {
        getCurrentSession().saveOrUpdate(bankParams);
    }

    @Override
    public BankParams getById(Integer id)
    {
        Session session = getCurrentSession();
        return (BankParams) session.get(BankParams.class, id);
    }

    @Override
    public void update(BankParams bankParams)
    {
        Session session = getCurrentSession();
        session.saveOrUpdate(bankParams);
    }

    @Override
    public BankParams getLast()
    {
       Session session = getCurrentSession();
       Query query = session.createQuery("from BankParams order by id DESC");
       query.setMaxResults(1);
       return  (BankParams) query.uniqueResult();
    }
}

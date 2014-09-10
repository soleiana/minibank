package com.minibank.core.repository;

import com.minibank.core.domain.BankParams;
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
    public void create(BankParams bankParams) throws DBException
    {
        getCurrentSession().saveOrUpdate(bankParams);
    }

    @Override
    public BankParams getById(Integer id) throws DBException
    {
        Session session = getCurrentSession();
        return (BankParams) session.get(BankParams.class, id);
    }

    @Override
    public void update(BankParams bankParams) throws DBException {
        Session session = getCurrentSession();
        session.saveOrUpdate(bankParams);
    }
}
